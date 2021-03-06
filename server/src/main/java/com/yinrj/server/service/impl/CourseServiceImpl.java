package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Course;
import com.yinrj.server.domain.CourseContent;
import com.yinrj.server.domain.CourseExample;
import com.yinrj.server.dto.CourseContentDto;
import com.yinrj.server.dto.CourseDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.SortDto;
import com.yinrj.server.mapper.CourseContentMapper;
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
    private CourseContentMapper courseContentMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

    @Override
    public PageDto<CourseDto> getList(PageDto<CourseDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        CourseExample example = new CourseExample();
        example.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(example);
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

    @Override
    public CourseContentDto getCourseContentByCourseId(String courseId) {
        CourseContent courseContent = courseContentMapper.selectByPrimaryKey(courseId);
        if (courseContent == null) {
            return null;
        }
        return CopyUtil.copy(courseContent, CourseContentDto.class);
    }

    @Override
    public int saveContent(CourseContentDto courseContentDto) {
        CourseContent courseContent = CopyUtil.copy(courseContentDto, CourseContent.class);
        // 先去更新，如果没有更新到则去insert
        // updateByPrimaryKeyWithBLOBs是对于有mediumtext数据类型的时候生成的方法
        int i = courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);
        if (i == 0) {
            i = courseContentMapper.insert(courseContent);
        }
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sort(SortDto sortDto) {
        myCourseMapper.updateSort(sortDto);

        if (sortDto.getOldSort() > sortDto.getNewSort()) {
            myCourseMapper.moveSortsForward(sortDto);
        }

        if (sortDto.getNewSort() > sortDto.getOldSort()) {
            myCourseMapper.moveSortsBackward(sortDto);
        }
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