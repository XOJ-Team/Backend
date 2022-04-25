package com.xoj.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LevelEnum {
    EASY(0, "easy"),
    MEDIUM(1, "medium"),
    HARD(2, "hard");

    private Integer code;

    private String description;

    public static String getDescription(Integer code) {
        LevelEnum[] levels = LevelEnum.values();
        for (LevelEnum level : levels) {
            if (level.code.equals(code)){
                return level.getDescription();
            }
        }
        return null;
    }
}
