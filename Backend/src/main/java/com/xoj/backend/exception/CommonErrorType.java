package com.xoj.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 1iin
 */
@Getter
@AllArgsConstructor
public enum CommonErrorType {
    SUCCESS("200", "Success"),
    BODY_NOT_MATCH("400", "Data Format Not Match"),
    SIGNATURE_NOT_MATCH("401", "Digital Signature Not Match"),
    NOT_FOUND("404", "Resource Not Found"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    SERVER_BUSY("503", "Server Busy"),

    FILE_EMPTY("20001", "File Name Empty"),
    UPLOAD_FAIL("20002", "Uploading Fail"),

    QUESTION_NOT_FOUND("30001", "Question Not Found");

    /**
     * Error Code
     */
    private String resultCode;

    /**
     * Error Message
     */
    private String resultMsg;
}
