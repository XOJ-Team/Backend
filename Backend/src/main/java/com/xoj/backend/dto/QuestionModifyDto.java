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
public class QuestionModifyDto {
    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    private Long modifier;

    private String modifierName;

    @NotEmpty
    private String content;
}
