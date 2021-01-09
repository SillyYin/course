package com.yinrj.business.controller.admin;

import com.yinrj.server.dto.CategoryDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class CategoryController {
    public static final String BUSINESS_NAME = "小节";

    @Resource
    private CategoryService categoryService;

    @PostMapping("/category/list")
    public ResponseDto<PageDto<CategoryDto>> categoryList(@RequestBody PageDto<CategoryDto> pageDto) {
        ResponseDto<PageDto<CategoryDto>> responseDto = new ResponseDto<>();
        responseDto.setContent(categoryService.getList(pageDto));
        return responseDto;
    }

    @PostMapping("/category/all")
    public ResponseDto<List<CategoryDto>> getAllCategory() {
        ResponseDto<List<CategoryDto>> responseDto = new ResponseDto<>();
        responseDto.setContent(categoryService.getAllCategory());
        return responseDto;
    }

    @PostMapping("/category/save")
    public ResponseDto<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        ResponseDto<CategoryDto> responseDto = new ResponseDto<>();

        // 保存校验

        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);
        return responseDto;
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseDto<CategoryDto> delete(@PathVariable String id) {
        ResponseDto<CategoryDto> responseDto = new ResponseDto<>();
        categoryService.delete(id);
        return responseDto;
    }
}
