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

    QUESTION_NOT_FOUND("30001", "Question Not Found"),
    COMPETITION_NOT_FOUND("30002", "Competition Not Found"),
    SUBMIT_RECORD_NOT_FOUND("30003", "Submit Record Not Found"),

    PARSE_ERROR("40001", "String to Date Error"),
    DUPLICATE_KEY("40002", "Duplicate Key for Link"),
    NO_USER("40003", "No User")
    ;

    /**
     * Error Code
     */
    private String resultCode;

    /**
     * Error Message
     */
    private String resultMsg;
}
