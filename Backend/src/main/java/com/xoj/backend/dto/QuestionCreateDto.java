package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDto {
    @NotEmpty
    private String name;

    private String creator;

    @NotNull
    private Integer questionLevel;

    private String tags;

    private String creatorName;

    private Boolean isHide;

    @NotEmpty
    private String content;
}
