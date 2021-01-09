package com.yinrj.business.controller.admin;

import com.yinrj.server.dto.CourseDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.service.CourseService;
import com.yinrj.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Yin
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class CourseController {
    public static final String BUSINESS_NAME = "小节";

    @Resource
    private CourseService courseService;

    @PostMapping("/course/list")
    public ResponseDto<PageDto<CourseDto>> courseList(@RequestBody PageDto<CourseDto> pageDto) {
        ResponseDto<PageDto<CourseDto>> responseDto = new ResponseDto<>();
        responseDto.setContent(courseService.getList(pageDto));
        return responseDto;
    }

    @PostMapping("/course/save")
    public ResponseDto<CourseDto> save(@RequestBody CourseDto courseDto) {
        ResponseDto<CourseDto> responseDto = new ResponseDto<>();

        // 保存校验
        ValidatorUtil.require(courseDto.getName(), "课程名");
        ValidatorUtil.length(courseDto.getName(), "课程名", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseDto<CourseDto> delete(@PathVariable String id) {
        ResponseDto<CourseDto> responseDto = new ResponseDto<>();
        courseService.delete(id);
        return responseDto;
    }
}
