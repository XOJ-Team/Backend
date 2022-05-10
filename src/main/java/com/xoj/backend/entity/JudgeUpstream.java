package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeUpstream implements Serializable {

    @NotNull
    private UUID token;

    @Nullable
    private Status status;

    @Nullable
    private Float memory;

    @Nullable
    private Float time;

    @Nullable
    private String stdout;

    @Nullable
    private String stderr;

    @Nullable
    private String compile_output;
}

