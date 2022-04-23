package com.xoj.backend.param;

import com.xoj.backend.common.LevelEnum;
import com.xoj.backend.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsSearchParam {
    private Long id;

    private Boolean isDelete;

    private String name;

    private Integer questionLevel;

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

    private Long total;

    private Long accept;

    private String rate;

    private Integer timeLimit;

    private Integer memoryLimit;

    private String searchKey;

    private String searchKey_keyword;

    public EsSearchParam(Question question, String searchKey) {
        this.id = question.getId();
        this.isDelete = question.getIsDelete();
        this.name = question.getName();
        this.questionLevel = question.getQuestionLevel();
        this.tags = question.getTags();
        this.createTime = question.getCreateTime();
        this.deleteTime = question.getDeleteTime();
        this.creator = question.getCreator();
        this.creatorName = question.getCreatorName();
        this.modifier = question.getModifier();
        this.modifierName = question.getModifierName();
        this.modifyTime = question.getModifyTime();
        this.isHide = question.getIsHide();
        this.content = question.getContent();
        this.total = question.getTotal();
        this.accept = question.getAccept();
        this.rate = question.getRate();
        this.timeLimit = question.getTimeLimit();
        this.memoryLimit = question.getMemoryLimit();
        this.searchKey = searchKey;
        this.searchKey_keyword = searchKey;
    }


}
