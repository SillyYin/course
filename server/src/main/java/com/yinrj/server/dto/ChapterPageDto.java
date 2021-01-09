package com.yinrj.server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yin
 * @date 2021/1/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterPageDto <T> extends PageDto<T> {
    private String courseId;
}
