package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Category;
import com.yinrj.server.domain.CategoryExample;
import com.yinrj.server.dto.CategoryDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.CategoryMapper;
import com.yinrj.server.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private static final String PARENT_CATEGORY_ID = "00000000";

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
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        deleteChildren(id);
        categoryMapper.deleteByPrimaryKey(id);
    }

    public void deleteChildren(String id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if (PARENT_CATEGORY_ID.equals(category.getParent())) {
            CategoryExample example = new CategoryExample();
            example.createCriteria().andParentEqualTo(category.getId());
            categoryMapper.deleteByExample(example);
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return CopyUtil.copyList(categoryList, CategoryDto.class);
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