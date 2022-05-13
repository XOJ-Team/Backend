package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaygroundResponseParam {
    @NotNull
    private JudgeStatus status;

    @NotNull
    private long question_id;

    @Nullable
    private String compile_output;

    @Nullable
    private String stdout;

    @Nullable
    private Double memory;

    @Nullable
    private Double time;

    public PlaygroundResponseParam(JudgeParam param) {
        status = param.getStatus();
        compile_output = param.getCompile_output();
        stdout = param.getStdout();
        memory = param.getMemory();
        time = param.getTime();
    }
}
