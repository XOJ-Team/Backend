package com.xoj.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    ACCEPTED(0, "Accepted"),
    COMPILE_ERROR(1, "Compile Error"),
    WRONG_ANSWER(2, "Wrong Answer"),
    OUTPUT_LIMIT(3, "Output Limit"),
    PRESENTATION_ERROR(4, "Presentation Error"),
    TIME_LIMITED_EXCEED(5, "Time Limited Exceed"),
    RUNTIME_ERROR(6, "Runtime Error")
    ;

    private Integer code;
    private String description;

    public static String getDescription(Integer code) {
        ResultEnum[] resultEnums = ResultEnum.values();
        for (ResultEnum resultEnum : resultEnums) {
            if (resultEnum.code.equals(code)){
                return resultEnum.getDescription();
            }
        }
        return null;
    }

}
