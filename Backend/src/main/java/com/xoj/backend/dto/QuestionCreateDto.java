package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDto {
    @NotEmpty
    private String name;

    private String creator;

    private String creatorName;

    @NotEmpty
    private String content;
}
