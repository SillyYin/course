package com.yinrj.server.dto;

import lombok.Data;

/**
 * @author Yin
 * @date 2020/12/27
 */
@Data
public class ResponseDto<T> {
    /**
     * 业务上的成功或者失败
     */
    private boolean success = true;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回的泛型数据
     */
    private T content;
}
