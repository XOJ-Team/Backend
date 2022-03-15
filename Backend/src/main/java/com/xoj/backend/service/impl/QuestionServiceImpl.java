package com.xoj.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xoj.backend.common.CommonConstants;
import com.xoj.backend.dto.QuestionCreateDto;
import com.xoj.backend.dto.QuestionModifyDto;
import com.xoj.backend.dto.QuestionPageDto;
import com.xoj.backend.entity.Question;
import com.xoj.backend.entity.User;
import com.xoj.backend.mapper.QuestionMapper;
import com.xoj.backend.service.QuestionService;
import com.xoj.backend.service.UserBaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final UserBaseService userBaseService;

    private final QuestionMapper mapper;

    /**
     * create a question
     *
     * @param dto
     */
    @Override
    public void create(QuestionCreateDto dto) {
        User user = userBaseService.getCurrentUser();
        Question question = Question.builder()
                .content(dto.getContent())
                .creator(user.getId())
                .creatorName(user.getName())
                .name(dto.getName())
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
        User user = userBaseService.getCurrentUser();
        Question question = Question.builder()
                .content(dto.getContent())
                .name(dto.getName())
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
            return mapper.selectOneByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
     * delete a question
     * @param id
     */
    @Override
    public void delete(Long id) {
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", id);
        Question question = Question.builder()
                .isDelete(CommonConstants.IS_DELETED)
                .deleteTime(new Date())
                .build();
        mapper.updateByExampleSelective(question, example);
    }
}
