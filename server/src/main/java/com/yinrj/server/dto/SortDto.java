package com.yinrj.server.dto;

import lombok.Data;

/**
 * @author Yin
 * @date 2021/1/10
 */
@Data
public class SortDto {
    private String id;
    private int oldSort;
    private int newSort;
}
