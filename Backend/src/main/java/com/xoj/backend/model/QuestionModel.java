package com.xoj.backend.model;

import com.xoj.backend.common.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel {
    private Long id;

    private Boolean isDelete;

    private String name;

    private Integer questionLevel;

    private String levelDescription;

    private String tags;

    private Date createTime;

    private Date deleteTime;

    private Long creator;

    private String creatorName;

    private Long modifier;

    private String modifierName;

    private Date modifyTime;

    private Boolean isHide;

    private String content;

    public String getLevelDescription() {
        return LevelEnum.getDescription(questionLevel);
    }
}
