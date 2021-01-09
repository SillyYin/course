package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Category;
import com.yinrj.server.dto.CategoryDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.CategoryMapper;
import com.yinrj.server.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public PageDto<CategoryDto> getList(PageDto<CategoryDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<Category> categoryList = categoryMapper.selectByExample(null);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        pageDto.setData(categoryDtoList);
        return pageDto;
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = CopyUtil.copy(categoryDto, Category.class);
        if (StringUtils.isEmpty(categoryDto.getId())) {
            String categoryId = addCategory(category);
            categoryDto.setId(categoryId);
        } else {
            updateCategory(category);
        }
    }

    @Override
    public void delete(String id) {
        categoryMapper.deleteByPrimaryKey(id);
    }


    private String addCategory(Category category) {
        String categoryId = UuidUtil.getShortUuid();
        category.setId(categoryId);
        categoryMapper.insert(category);
        return categoryId;
    }

    private void updateCategory(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }
}