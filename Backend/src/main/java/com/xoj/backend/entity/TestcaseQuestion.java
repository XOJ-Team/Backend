package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestcaseQuestion {
    @Id
    private Long id;

    @Column(name = "question_id")
    private Long questionId;

    private String testcase;

    private String result;

    @Column(name = "is_delete")
    private Boolean isDelete;

    private Long creator;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "modifier_name")
    private String modifierName;

    private Long modifier;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "delete_time")
    private Date deleteTime;
}