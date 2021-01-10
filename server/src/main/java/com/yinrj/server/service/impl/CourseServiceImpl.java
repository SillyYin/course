package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Course;
import com.yinrj.server.dto.CourseDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.CourseMapper;
import com.yinrj.server.mapper.my.MyCourseMapper;
import com.yinrj.server.service.CourseCategoryService;
import com.yinrj.server.service.CourseService;
import com.yinrj.server.util.CopyUtil;
import com.yinrj.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

    @Override
    public PageDto<CourseDto> getList(PageDto<CourseDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<Course> courseList = courseMapper.selectByExample(null);
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<CourseDto> courseDtoList = CopyUtil.copyList(courseList, CourseDto.class);
        pageDto.setData(courseDtoList);
        return pageDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CourseDto courseDto) {
        Date now = new Date();
        courseDto.setUpdatedAt(now);
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(courseDto.getId())) {
            String courseId = addCourse(course);
            courseDto.setId(courseId);
        } else {
            updateCourse(course);
        }

        // 批量保存分类
        courseCategoryService.saveBatch(courseDto.getId(), courseDto.getCategorys());
    }

    @Override
    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }


    @Override
    public void updateCourseTime(String courseId) {
        log.debug("更新课程时常: {}", courseId);
        myCourseMapper.updateTime(courseId);
    }


    private String addCourse(Course course) {
        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        String courseId = UuidUtil.getShortUuid();
        course.setId(courseId);
        courseMapper.insert(course);
        return courseId;
    }

    private void updateCourse(Course course) {
        Course selectedCourse = courseMapper.selectByPrimaryKey(course.getId());
        course.setUpdatedAt(new Date());
        course.setCreatedAt(selectedCourse.getCreatedAt());
        courseMapper.updateByPrimaryKey(course);
    }
}