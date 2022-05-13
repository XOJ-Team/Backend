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
public class PlaygroundRequestParam {

    @NotNull
    private long question_id;

    @NotNull
    private int language_id;

    @NotNull
    private String source_code;

    @NotNull
    private String stdin;

}
