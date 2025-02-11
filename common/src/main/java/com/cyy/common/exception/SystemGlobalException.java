package com.cyy.common.exception;

import com.cyy.common.enums.ErrorCode;

public class SystemGlobalException extends GlobalException{
    public SystemGlobalException() {
        super(ErrorCode.SYSTEM_ERROR);
    }
}
