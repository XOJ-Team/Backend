package com.xoj.backend.model;

import com.xoj.backend.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 1iin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionModel {

    private Long id;

    private Boolean isDelete;

    private String name;

    private String startTime;

    private String endTime;

    private Date createTime;

    private Date deleteTime;

    private String briefIntroduction;

    private Long creator;

    private String creatorName;
}
