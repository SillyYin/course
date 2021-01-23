package com.yinrj.business.controller.admin;

import com.yinrj.server.dto.CourseContentFileDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.service.CourseContentFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/admin/course-content-file")
@Slf4j
public class CourseContentFileController {
    public static final String BUSINESS_NAME = "课程内容文件";

    @Resource
    private CourseContentFileService courseContentFileService;


    @GetMapping("/list/{courseId}")
    public ResponseDto<List<CourseContentFileDto>> courseContentFileList(@PathVariable String courseId) {
        ResponseDto<List<CourseContentFileDto>> responseDto = new ResponseDto<>();
        responseDto.setContent(courseContentFileService.list(courseId));
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto<CourseContentFileDto> save(@RequestBody CourseContentFileDto courseContentFileDto) {
        ResponseDto<CourseContentFileDto> responseDto = new ResponseDto<>();

        // 保存校验

        courseContentFileService.save(courseContentFileDto);
        responseDto.setContent(courseContentFileDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<CourseContentFileDto> delete(@PathVariable String id) {
        ResponseDto<CourseContentFileDto> responseDto = new ResponseDto<>();
        courseContentFileService.delete(id);
        return responseDto;
    }
}
