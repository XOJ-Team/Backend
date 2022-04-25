package com.xoj.backend.model;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPageModel {
    PageInfo<QuestionModel> questionsPage;

    List<Long> questionIds;
}
