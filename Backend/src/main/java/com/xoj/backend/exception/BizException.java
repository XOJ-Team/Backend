package com.xoj.backend.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 1iin
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(CommonErrorType commonEnum) {
        this.errorCode = commonEnum.getResultCode();
        this.errorMsg = commonEnum.getResultMsg();
    }

    public BizException(CommonErrorType commonEnum, Throwable cause) {
        super(commonEnum.getResultCode(), cause);
        this.errorCode = commonEnum.getResultCode();
        this.errorMsg = commonEnum.getResultMsg();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
