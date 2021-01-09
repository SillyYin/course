package com.yinrj.server.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * @author: Yin
 * @date: 2021/1/10
 */
public interface MyCourseMapper {
    int updateTime(@Param("courseId") String courseId);
}
