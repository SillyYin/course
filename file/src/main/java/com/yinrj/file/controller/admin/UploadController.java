package com.yinrj.file.controller.admin;

import com.yinrj.server.dto.FileDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.service.FileService;
import com.yinrj.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

    public static final String BUSINESS_NAME = "文件上传";

    @Resource
    private FileService fileService;

    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @Value("${file.path}")
    private String FILE_PATH;

    @PostMapping("/upload")
    public ResponseDto<String> upload(@RequestParam MultipartFile file) throws IOException {
        log.info("文件开始上传: {}", file);
        log.info(file.getOriginalFilename());
        log.info(String.valueOf(file.getSize()));

        String fileName = file.getOriginalFilename();
        String key = UuidUtil.getShortUuid();

        if (fileName == null) {
            throw new RuntimeException("文件名为空");
        }

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String path = key + "." + suffix;

        String fullPath = FILE_PATH + path;
        log.info("文件全路径：{}", fullPath);
        File dest = new File(fullPath);
        file.transferTo(dest);
        log.info(dest.getAbsolutePath());

        log.info("保存文件记录开始");
        FileDto fileDto = new FileDto();
        fileDto.setPath(path);
        fileDto.setName(fileName);
        fileDto.setSize(Math.toIntExact(file.getSize()));
        fileDto.setSuffix(suffix);
        fileDto.setUse("");
        fileService.save(fileDto);

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setContent(FILE_DOMAIN + key + "." + suffix);
        return responseDto;
    }
}
