package com.yinrj.server.service;

import com.yinrj.server.dto.CategoryDto;
import com.yinrj.server.dto.CourseCategoryDto;
import com.yinrj.server.dto.PageDto;

import java.util.List;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface CourseCategoryService {
    PageDto<CourseCategoryDto> getList(PageDto<CourseCategoryDto> pageDto);

    void save(CourseCategoryDto courseCategoryDto);

    void delete(String id);

    void saveBatch(String courseId, List<CategoryDto> dtoList);
}
