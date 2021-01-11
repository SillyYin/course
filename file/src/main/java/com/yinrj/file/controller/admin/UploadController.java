package com.yinrj.file.controller.admin;

import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Yin
 * @date 2021/1/11
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class UploadController {
    private static final String PRE_PATH = "/Users/yinrongjie/Desktop/file/";
    private static final String BUSINESS_NAME = "文件上传";

    @PostMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file) throws IOException {
        log.info("文件开始上传: {}", file);
        log.info(file.getOriginalFilename());
        log.info(String.valueOf(file.getSize()));

        String fileName = file.getOriginalFilename();;
        String key = UuidUtil.getShortUuid();
        String fullPath = PRE_PATH + key + "-" + fileName;
        File dest = new File(fullPath);
        file.transferTo(dest);
        log.info(dest.getAbsolutePath());

        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
