package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author 1iin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCompetitionPageDto {
    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

}