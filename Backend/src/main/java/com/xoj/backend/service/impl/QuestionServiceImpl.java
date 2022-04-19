package com.xoj.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xoj.backend.common.CommonConstants;
import com.xoj.backend.dto.QuestionCreateDto;
import com.xoj.backend.dto.QuestionModifyDto;
import com.xoj.backend.dto.QuestionPageDto;
import com.xoj.backend.entity.Question;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.mapper.QuestionMapper;
import com.xoj.backend.model.QuestionModel;
import com.xoj.backend.model.QuestionSearchModel;
import com.xoj.backend.param.EsSearchParam;
import com.xoj.backend.service.ElasticSearchService;
import com.xoj.backend.service.QuestionCompetitionService;
import com.xoj.backend.service.QuestionService;
import com.xoj.backend.service.UserBaseService;
import com.xoj.backend.util.JacksonUtils;
import com.xoj.backend.util.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.Formatter;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    ElasticSearchService elasticSearchService;

    private final UserBaseService userBaseService;

    private final QuestionCompetitionService questionCompetitionService;

    private final QuestionMapper mapper;

    private RedisUtils redisUtils;

    private final String prefix = "QUESTION";

    /**
     * create a question
     *
     * @param dto
     */
    @Override
    public void create(QuestionCreateDto dto) {
        if (null == dto.getIsHide()){
            dto.setIsHide(CommonConstants.IS_SHOW);
        }
        UserBase user = userBaseService.getCurrentUser();
        Question question = Question.builder()
                .content(dto.getContent())
                .creator(user.getId())
                .creatorName(user.getName())
                .name(dto.getName())
                .isHide(dto.getIsHide())
                .questionLevel(dto.getQuestionLevel())
                .timeLimit(dto.getTimeLimit())
                .memoryLimit(dto.getMemoryLimit())
                .tags(dto.getTags())
                .build();
        mapper.insertSelective(question);
        Question question_info = getQuestion(dto.getName());
        String searchKey = question_info.getTags() + " " + question_info.getName() + " " + question_info.getId();
        EsSearchParam esSearchParam = new EsSearchParam(question_info, searchKey);
        elasticSearchService.insertDocument(   "questions", dto.getName(),esSearchParam);

    }

    /**
     * modify the content and name of a question
     *
     * @param dto
     */
    @Override
    public void modify(QuestionModifyDto dto) {
        String key = prefix + dto.getId();
        if (redisUtils.hasKey(key)){
            redisUtils.delete(key);
        }
        UserBase user = userBaseService.getCurrentUser();
        Question question = Question.builder()
                .content(dto.getContent())
                .name(dto.getName())
                .isHide(dto.getIsHide())
                .modifier(user.getId())
                .modifierName(user.getName())
                .questionLevel(dto.getQuestionLevel())
                .timeLimit(dto.getTimeLimit())
                .memoryLimit(dto.getMemoryLimit())
                .tags(dto.getTags())
                .build();
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", dto.getId());
        mapper.updateByExampleSelective(question, example);
        Question question_info = getQuestion(dto.getName());
        String searchKey = question_info.getTags() + " " + question_info.getName() + " " + question_info.getId();
        EsSearchParam esSearchParam = new EsSearchParam(question_info, searchKey);
        elasticSearchService.insertDocument(   "questions", dto.getName(),esSearchParam);
    }

    /**
     * show one question
     *
     * @param id
     */
    @Override
    public QuestionModel selectOneQuestion(Long id) {
        try {
            String key = prefix + id;
            String str = null;
            if (redisUtils.hasKey(key)) {
                str = redisUtils.getValue(key);
            }
            if (StringUtils.hasText(str)) {
                return JacksonUtils.string2Obj(str, QuestionModel.class);
            } else {
                QuestionModel question = mapper.selectQuestion(id);
                if (null == question) {
                    throw new BizException(CommonErrorType.QUESTION_NOT_FOUND);
                }
                String JSONString = JacksonUtils.obj2String(question);
                redisUtils.set(key, JSONString);
                return question;
            }
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    /**
     * show questions
     *
     * @param dto
     * @return
     */
    @Override
    public PageInfo<QuestionModel> selectQuestions(QuestionPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<QuestionModel> questions = mapper.selectQuestions(dto.getIds());
        return PageInfo.of(questions);
    }

    /**
     * show all the questions
     * @param dto
     * @return
     */
    @Override
    public PageInfo<QuestionModel> selectAllQuestions(QuestionPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<QuestionModel> questions = mapper.selectAllQuestions();
        return PageInfo.of(questions);
    }

    @Override
    public PageInfo<QuestionModel> selectAllShowQuestions(QuestionPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<QuestionModel> questions = mapper.selectShowQuestions();
        return PageInfo.of(questions);
    }

    /**
     * delete a question
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        String key = prefix + id;
        if (redisUtils.hasKey(key)){
            redisUtils.delete(key);
        }
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", id);
        Question question = Question.builder()
                .isDelete(CommonConstants.IS_DELETED)
                .deleteTime(new Date())
                .build();
        mapper.updateByExampleSelective(question, example);
        try {
            Question question_info = getQuestion(id);
            elasticSearchService.deleteDocument("questions",question_info.getName());
        }catch (Exception ignore){}
        questionCompetitionService.deleteListByQuestion(id);
    }

    public Question getQuestion(Long id) {

        Example example = new Example(UserBase.class);
        example.createCriteria()
                .andEqualTo("id", id);
        List<Question> questions = mapper.selectByExample(example);
        if(CollectionUtils.isEmpty(questions)){
            return null;
        }else{
            Question question = questions.get(0);
            return question;
        }
    }
    public Question getQuestion(String name) {
        Example example = new Example(UserBase.class);
        example.createCriteria()
                .andEqualTo("name", name);
        List<Question> questions = mapper.selectByExample(example);
        if(CollectionUtils.isEmpty(questions)){
            return null;
        }else{
            Question question = questions.get(0);
            return question;
        }
    }
    @Override
    public void hide(Long id) {
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", id);
        Question question = Question.builder()
                .isHide(CommonConstants.IS_HIDE)
                .deleteTime(new Date())
                .build();
        mapper.updateByExampleSelective(question, example);
    }

    @Override
    public void show(Long id) {
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", id);
        Question question = Question.builder()
                .isHide(CommonConstants.IS_SHOW)
                .deleteTime(new Date())
                .build();
        mapper.updateByExampleSelective(question, example);
    }

    @Override
    public void calRate(Long id, Integer result) {
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", id);
        Question q = mapper.selectOneByExample(example);
        long accept = 0L;
        if (0 == result) {
            accept = q.getAccept() + 1;
        } else {
            accept = q.getAccept();
        }
        Question question = Question.builder()
                .total(q.getTotal() + 1)
                .accept(accept)
                .rate(new Formatter().format("%.2f", (double)accept * 100 / (double)(q.getTotal() + 1)) + "%")
                .build();
        mapper.updateByExampleSelective(question, example);

        Question question_info = getQuestion(id);
        String searchKey = question_info.getTags() + " " + question_info.getName() + " " + question_info.getId();
        EsSearchParam esSearchParam = new EsSearchParam(question_info, searchKey);
        elasticSearchService.insertDocument(   "questions", question_info.getName(),esSearchParam);
    }

    @Override
    public List<QuestionSearchModel> search(String text) {
        long number;
        List<QuestionSearchModel> questionSearchModels;
        try {
            number = Long.parseLong(text);
            questionSearchModels = mapper.selectSearch(number, text);
        } catch (NumberFormatException e) {
            questionSearchModels = mapper.selectSearch(null, text);
        }
        return questionSearchModels;
    }


}
