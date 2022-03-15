package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPageDto {
    private Integer pageNum;

    private Integer pageSize;

    List<Long> ids;
}
