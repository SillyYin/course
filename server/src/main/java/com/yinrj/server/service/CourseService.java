package com.yinrj.server.service;

import com.yinrj.server.dto.CourseContentDto;
import com.yinrj.server.dto.CourseDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.SortDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface CourseService {
    PageDto<CourseDto> getList(PageDto<CourseDto> pageDto);

    void save(CourseDto courseDto);

    void delete(String id);

    void updateCourseTime(String courseId);

    CourseContentDto getCourseContentByCourseId(String courseId);

    int saveContent(CourseContentDto courseContentDto);

    void sort(SortDto sortDto);
}
