package com.xoj.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    ACCEPTED(3, "Accepted"),
    WRONG_ANSWER(4, "Wrong Answer"),
    TIME_LIMITED_EXCEED(5, "Time Limit Exceeded"),
    COMPILE_ERROR(6, "Compilation Error"),
    RUNTIME_ERROR_SIGSEGV(7, "Runtime Error (SIGSEGV)"),
    RUNTIME_ERROR_SIGXFSZ(8, "Runtime Error (SIGXFSZ)"),
    RUNTIME_ERROR_SIGFPE(9, "Runtime Error (SIGFPE)"),
    RUNTIME_ERROR_SIGABRT(10, "Runtime Error (SIGABRT)"),
    RUNTIME_ERROR_NZEC(11, "Runtime Error (NZEC)"),
    RUNTIME_ERROR_OTHER(12, "Runtime Error (Other)"),
    INTERNAL_ERROR(13, "Internal Error"),
    EXEC_FORMAT_ERROR(14, "Exec Format Error")
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
