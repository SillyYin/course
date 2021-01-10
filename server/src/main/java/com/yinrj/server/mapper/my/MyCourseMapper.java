package com.yinrj.server.mapper.my;

import com.yinrj.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Yin
 * @date: 2021/1/10
 */
public interface MyCourseMapper {
    int updateTime(@Param("courseId") String courseId);
    int updateSort(SortDto sortDto);
    int moveSortsForward(SortDto sortDto);
    int moveSortsBackward(SortDto sortDto);
}
