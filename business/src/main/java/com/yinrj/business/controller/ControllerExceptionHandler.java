package com.yinrj.business.controller;

import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.exception.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yin
 * @date 2020/12/27
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandle(ValidatorException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        log.warn(e.getMessage());
        responseDto.setMessage("请求参数异常！");
        return responseDto;
    }
}
