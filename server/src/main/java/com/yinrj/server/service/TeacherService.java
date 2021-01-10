package com.yinrj.server.service;

import com.yinrj.server.dto.TeacherDto;
import com.yinrj.server.dto.PageDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface TeacherService {
    PageDto<TeacherDto> getList(PageDto<TeacherDto> pageDto);

    void save(TeacherDto teacherDto);

    void delete(String id);
}
