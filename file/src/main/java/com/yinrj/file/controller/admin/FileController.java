package com.yinrj.file.controller.admin;

import com.yinrj.server.dto.FileDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.service.FileService;
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
public class FileController {
    public static final String BUSINESS_NAME = "文件";

    @Resource
    private FileService fileService;

    @PostMapping("/file/list")
    public ResponseDto<PageDto<FileDto>> fileList(@RequestBody PageDto<FileDto> pageDto) {
        ResponseDto<PageDto<FileDto>> responseDto = new ResponseDto<>();
        responseDto.setContent(fileService.getList(pageDto));
        return responseDto;
    }
}
