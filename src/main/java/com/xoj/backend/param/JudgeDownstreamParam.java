package com.xoj.backend.param;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JudgeDownstreamParam {

    @NotNull
    private int lang_id;

    @NotNull
    private String code;

    public int getLangId() {
        return this.lang_id;
    }

    public String getCode() {
        return this.code;
    }

}
