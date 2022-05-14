package com.xoj.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageEnum {
    C11(50, "C"),
    CPP14(54, "C++"),
    GO(60, "Golang"),
    JAVA(62, "Java"),
    PYTHON2(70, "Python 2.7"),
    PYTHON3(71, "Python 3.6")
    ;

    private Integer code;
    private String description;

    public static String getDescription(Integer code) {
        LanguageEnum[] langEnums = LanguageEnum.values();
        for (LanguageEnum lang : langEnums) {
            if (lang.code.equals(code)){
                return lang.getDescription();
            }
        }
        return null;
    }
}
