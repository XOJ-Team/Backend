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
import com.xoj.backend.mapper.QuestionMapper;
import com.xoj.backend.service.QuestionService;
import com.xoj.backend.service.UserBaseService;
import com.xoj.backend.util.JacksonUtils;
import com.xoj.backend.util.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final UserBaseService userBaseService;

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
                .build();
        mapper.insertSelective(question);
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
                .build();
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", dto.getId());
        mapper.updateByExampleSelective(question, example);
    }

    /**
     * show one question
     *
     * @param id
     */
    @Override
    public Question selectOneQuestion(Long id) {
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", id);
        try {
            String key = prefix + id;
            String str = null;
            if (redisUtils.hasKey(key)) {
                str = redisUtils.getValue(key);
            }
            if (StringUtils.hasText(str)) {
                return JacksonUtils.string2Obj(str, Question.class);
            } else {
                Question question = mapper.selectOneByExample(example);
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
    public PageInfo<Question> selectQuestions(QuestionPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Question> questions = mapper.selectQuestions(dto.getIds());
        return PageInfo.of(questions);
    }

    /**
     * show all the questions
     * @param dto
     * @return
     */
    @Override
    public PageInfo<Question> selectAllQuestions(QuestionPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        Example example = new Example(Question.class);
        example.createCriteria()
                .andEqualTo("isDelete", CommonConstants.NOT_DELETED);
        List<Question> questions = mapper.selectByExample(example);
        return PageInfo.of(questions);
    }

    @Override
    public PageInfo<Question> selectAllShowQuestions(QuestionPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        Example example = new Example(Question.class);
        example.createCriteria()
                .andEqualTo("isDelete", CommonConstants.NOT_DELETED)
                .andEqualTo("isHide", CommonConstants.IS_SHOW);
        List<Question> questions = mapper.selectByExample(example);
        return PageInfo.of(questions);
    }

    /**
     * delete a question
     * @param id
     */
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


}
