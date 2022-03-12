package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private Boolean isDelete;

    private String name;

    private Date createTime;

    private Date deleteTime;

    private String mail;

    private String phoneNumber;

    private Integer score;

    private Integer rank;

    private Byte authority;

}