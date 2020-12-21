package com.yinrj.server.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Yin
 * @date 2020/12/21
 */
@Data
public class PageDto <T> {
    private int pageNum;
    private int pageSize;
    private List<T> data;
    private long totalCount;
}
