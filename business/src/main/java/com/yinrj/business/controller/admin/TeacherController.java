package com.yinrj.business.controller.admin;

import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.dto.TeacherDto;
import com.yinrj.server.service.TeacherService;
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
public class TeacherController {
    public static final String BUSINESS_NAME = "讲师";

    @Resource
    private TeacherService teacherService;

    @PostMapping("/teacher/list")
    public ResponseDto<PageDto<TeacherDto>> teacherList(@RequestBody PageDto<TeacherDto> pageDto) {
        ResponseDto<PageDto<TeacherDto>> responseDto = new ResponseDto<>();
        responseDto.setContent(teacherService.getList(pageDto));
        return responseDto;
    }

    @PostMapping("/teacher/save")
    public ResponseDto<TeacherDto> save(@RequestBody TeacherDto teacherDto) {
        ResponseDto<TeacherDto> responseDto = new ResponseDto<>();

        // 保存校验
        ValidatorUtil.require(teacherDto.getName(), "讲师姓名");
        ValidatorUtil.length(teacherDto.getName(), "讲师姓名", 1, 50);

        teacherService.save(teacherDto);
        responseDto.setContent(teacherDto);
        return responseDto;
    }

    @DeleteMapping("/teacher/delete/{id}")
    public ResponseDto<TeacherDto> delete(@PathVariable String id) {
        ResponseDto<TeacherDto> responseDto = new ResponseDto<>();
        teacherService.delete(id);
        return responseDto;
    }
}
