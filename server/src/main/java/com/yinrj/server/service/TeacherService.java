package com.yinrj.server.service;

import com.yinrj.server.dto.TeacherDto;
import com.yinrj.server.dto.PageDto;

import java.util.List;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface TeacherService {
    PageDto<TeacherDto> getList(PageDto<TeacherDto> pageDto);

    void save(TeacherDto teacherDto);

    void delete(String id);

    List<TeacherDto> getAllTeachers();
}
