package com.shaylee.shirojwt.exception;

import com.shaylee.shirojwt.base.BaseException;
import com.shaylee.shirojwt.result.ErrorResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Title: APP端认证授权异常处理器
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorResult handleException(Exception e) {
        String code = null;
        String message = e.getMessage();
        if (StringUtils.isBlank(message)) {
            message = "Sorry, problem occurs. Please contact our staff for help.";
        }
        if (e instanceof BaseException) {
            BaseException businessException = (BaseException) e;
            code = businessException.getCode();
        } else {
            code = ErrorCode.BASE_ERROR_CODE;
        }
        logger.error("Error Code:{} ", code, e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResult result = new ErrorResult();
        result.setErrorCode(code);
        result.setStatus(status.value());
        result.setError(status.getReasonPhrase());
        // TODO 开发阶段弹出错误码
        message += "ErrorCode:";
        message += code;
        result.setMessage(message);
        return result;
    }

    /**
     * 捕捉ShiroException，token失效
     *
     * @param e ShiroException
     * @return 响应结果
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ErrorResult handle401(ShiroException e) {
        return new ErrorResult(ErrorCode.UNAUTHORIZED, "authorized error");
    }
}