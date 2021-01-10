package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Teacher;
import com.yinrj.server.dto.TeacherDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.TeacherMapper;
import com.yinrj.server.service.TeacherService;
import com.yinrj.server.util.CopyUtil;
import com.yinrj.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public PageDto<TeacherDto> getList(PageDto<TeacherDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<Teacher> teacherList = teacherMapper.selectByExample(null);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList, TeacherDto.class);
        pageDto.setData(teacherDtoList);
        return pageDto;
    }

    @Override
    public void save(TeacherDto teacherDto) {
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        if (StringUtils.isEmpty(teacherDto.getId())) {
            String teacherId = addTeacher(teacher);
            teacherDto.setId(teacherId);
        } else {
            updateTeacher(teacher);
        }
    }

    @Override
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }


    private String addTeacher(Teacher teacher) {
        String teacherId = UuidUtil.getShortUuid();
        teacher.setId(teacherId);
        teacherMapper.insert(teacher);
        return teacherId;
    }

    private void updateTeacher(Teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }
}