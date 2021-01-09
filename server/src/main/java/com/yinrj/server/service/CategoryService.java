package com.yinrj.server.service;

import com.yinrj.server.dto.CategoryDto;
import com.yinrj.server.dto.PageDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface CategoryService {
    PageDto<CategoryDto> getList(PageDto<CategoryDto> pageDto);

    void save(CategoryDto categoryDto);

    void delete(String id);
}
