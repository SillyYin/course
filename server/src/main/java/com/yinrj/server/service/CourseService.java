package com.yinrj.server.service;

import com.yinrj.server.dto.CourseDto;
import com.yinrj.server.dto.PageDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface CourseService {
    PageDto<CourseDto> getList(PageDto<CourseDto> pageDto);

    void save(CourseDto courseDto);

    void delete(String id);
}
