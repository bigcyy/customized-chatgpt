package com.cyy.chat.config;



import com.cyy.common.enums.ErrorCode;
import com.cyy.common.exception.GlobalException;
import com.cyy.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 * @author winter
 * @author CYY
 * @create 2022-04-03 下午8:33
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public R error(GlobalException e) {
        log.error("全局异常捕获: {}", e.getMessage(), e);
        return R.error(e.getCode(),e.getMessage());
    }

    /**
     * 拦截统一的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R error(RuntimeException e) {
        log.error("全局异常捕获: {}", e.getMessage(), e);
        return R.error(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 拦截所有未被捕捉到的异常
     */
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        log.error("全局异常捕获: {}", e.getMessage(), e);
        return R.error(ErrorCode.SYSTEM_ERROR);
    }

}