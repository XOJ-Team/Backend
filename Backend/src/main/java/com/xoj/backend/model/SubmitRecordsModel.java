package com.xoj.backend.model;

import com.xoj.backend.common.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitRecordsModel {

    private Long id;

    private Long questionId;

    private String questionName;

    private Long userId;

    private Date createTime;

    private String lang;

    private Integer result;

    private String resultDescription;

    private Integer timeCost;

    private Double memoryCost;

    private String comments;

    private String codes;

    public String getResultDescription() {
        return ResultEnum.getDescription(result);
    }
}
