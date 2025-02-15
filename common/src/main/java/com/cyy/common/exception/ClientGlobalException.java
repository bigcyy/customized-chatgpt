package com.cyy.common.exception;

import com.cyy.common.enums.ErrorCode;

public class ClientGlobalException extends GlobalException{
    public ClientGlobalException() {
        super(ErrorCode.CLIENT_ERROR);
    }

    public ClientGlobalException(String errorMsg) {
        super(ErrorCode.CLIENT_ERROR, errorMsg);
    }

    public ClientGlobalException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ClientGlobalException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
