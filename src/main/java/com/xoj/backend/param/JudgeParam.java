package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeParam {

    @NotNull
    private UUID token;

    @NotNull
    private JudgeStatus status;

    @Nullable
    private Integer language_id;

    @Nullable
    private String source_code;

    @Nullable
    private Double memory_limit;

    @Nullable
    private Double memory;

    @Nullable
    private Double cpu_time_limit;

    @Nullable
    private Double time;

//    @Nullable
//    private String finished_at;

    @Nullable
    private String stdin;

    @Nullable
    private String stdout;

    @Nullable
    private String stderr;

    @Nullable
    private String expected_output;

    @Nullable
    private String compile_output;

    public JudgeParam(PlaygroundRequestParam param) {
        source_code = param.getSource_code();
        language_id = param.getLanguage_id();
        stdin = param.getStdin();
    }
}

