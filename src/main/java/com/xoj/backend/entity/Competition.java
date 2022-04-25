package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 1iin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition implements Serializable {
    @Id
    private Long id;

    @Column(name = "is_delete")
    private Boolean isDelete;

    private String name;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "delete_time")
    private Date deleteTime;

    @Column(name = "brief_introduction")
    private String briefIntroduction;

    private Long creator;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

}
