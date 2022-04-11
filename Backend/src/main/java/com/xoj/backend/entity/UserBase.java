package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBase implements Serializable {
    @Id
    private Long id;

    private Boolean isDelete;

    private String name;

    private Date createTime;

    private Date deleteTime;

    private String mail;

    private String phoneNumber;

    private Integer score;

    private Integer ranking;

    private Byte authority;

    private String password;

    @Column(name = "solved_number")
    private Integer solvedNumber;

    @Column(name = "easy_number")
    private Integer easyNumber;

    @Column(name = "medium_number")
    private Integer mediumNumber;

    @Column(name = "hard_number")
    private Integer hardNumber;

    @Column(name = "profile_photo")
    private String profilePhoto;

    private String intro;

    private List<Long> solved;
}