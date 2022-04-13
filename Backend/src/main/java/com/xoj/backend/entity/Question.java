package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    @Id
    private Long id;

    @Column(name = "is_delete")
    private Boolean isDelete;

    private String name;

    @Column(name = "question_level")
    private Integer questionLevel;

    private String tags;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "delete_time")
    private Date deleteTime;

    private Long creator;

    @Column(name = "creator_name")
    private String creatorName;

    private Long modifier;

    @Column(name = "modifier_name")
    private String modifierName;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "is_hide")
    private Boolean isHide;

    private String content;

    private Long total;

    private Long accept;

    private Double rate;
}