package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @NotNull
    private Date finished_at;

    public PlaygroundResponseParam(JudgeParam result, PlaygroundRequestParam request) {
        question_id = request.getQuestion_id();
        status = result.getStatus();
        compile_output = result.getCompile_output();
        stdout = result.getStdout();
        memory = result.getMemory();
        time = result.getTime();
        finished_at = new Date();
    }
}
