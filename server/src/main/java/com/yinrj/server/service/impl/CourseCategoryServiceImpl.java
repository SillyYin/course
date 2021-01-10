package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.CourseCategory;
import com.yinrj.server.domain.CourseCategoryExample;
import com.yinrj.server.dto.CategoryDto;
import com.yinrj.server.dto.CourseCategoryDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.CourseCategoryMapper;
import com.yinrj.server.service.CourseCategoryService;
import com.yinrj.server.util.CopyUtil;
import com.yinrj.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {
    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageDto<CourseCategoryDto> getList(PageDto<CourseCategoryDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(null);
        PageInfo<CourseCategory> pageInfo = new PageInfo<>(courseCategoryList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<CourseCategoryDto> courseCategoryDtoList = CopyUtil.copyList(courseCategoryList, CourseCategoryDto.class);
        pageDto.setData(courseCategoryDtoList);
        return pageDto;
    }

    @Override
    public void save(CourseCategoryDto courseCategoryDto) {
        CourseCategory courseCategory = CopyUtil.copy(courseCategoryDto, CourseCategory.class);
        if (StringUtils.isEmpty(courseCategoryDto.getId())) {
            String courseCategoryId = addCourseCategory(courseCategory);
            courseCategoryDto.setId(courseCategoryId);
        } else {
            updateCourseCategory(courseCategory);
        }
    }

    @Override
    public void delete(String id) {
        courseCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatch(String courseId, List<CategoryDto> dtoList) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        // 删除这门课程本来属于的分类
        courseCategoryMapper.deleteByExample(example);
        for (CategoryDto categoryDto : dtoList) {
            CourseCategory courseCategory = new CourseCategory();
            courseCategory.setCategoryId(categoryDto.getId());
            courseCategory.setCourseId(courseId);
            addCourseCategory(courseCategory);
        }
    }

    /**
     * 根据课程Id查找其所有分类
     * @param courseId
     * @return
     */
    @Override
    public List<CourseCategoryDto> listByCourseId(String courseId) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(example);
        return CopyUtil.copyList(courseCategoryList, CourseCategoryDto.class);
    }


    private String addCourseCategory(CourseCategory courseCategory) {
        String courseCategoryId = UuidUtil.getShortUuid();
        courseCategory.setId(courseCategoryId);
        courseCategoryMapper.insert(courseCategory);
        return courseCategoryId;
    }

    private void updateCourseCategory(CourseCategory courseCategory) {
        courseCategoryMapper.updateByPrimaryKey(courseCategory);
    }
}