package com.yinrj.server.service;

import com.yinrj.server.dto.CourseContentFileDto;
import com.yinrj.server.dto.PageDto;

import java.util.List;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface CourseContentFileService {
    PageDto<CourseContentFileDto> getList(PageDto<CourseContentFileDto> pageDto);

    void save(CourseContentFileDto courseContentFileDto);

    void delete(String id);

    List<CourseContentFileDto> list(String courseId);
}
