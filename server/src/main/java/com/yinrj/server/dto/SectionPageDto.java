package com.yinrj.server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yin
 * @date 2021/1/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SectionPageDto <T> extends PageDto<T> {
    private String courseId;
    private String chapterId;
}
