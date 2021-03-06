package com.yinrj.business.controller.admin;

import com.yinrj.server.dto.ChapterDto;
import com.yinrj.server.dto.ChapterPageDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.service.ChapterService;
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
public class ChapterController {
    public static final String BUSINESS_NAME = "大章";

    @Resource
    private ChapterService chapterService;

    @PostMapping("/chapter/list")
    public ResponseDto<PageDto<ChapterDto>> chapterList(@RequestBody ChapterPageDto<ChapterDto> pageDto) {
        ResponseDto<PageDto<ChapterDto>> responseDto = new ResponseDto<>();
        ValidatorUtil.require(pageDto.getCourseId(), "课程ID");
        responseDto.setContent(chapterService.getList(pageDto));
        return responseDto;
    }

    @PostMapping("/chapter/save")
    public ResponseDto<ChapterDto> save(@RequestBody ChapterDto chapterDto) {
        ResponseDto<ChapterDto> responseDto = new ResponseDto<>();

        // 保存校验
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);

        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping("/chapter/delete/{id}")
    public ResponseDto<ChapterDto> delete(@PathVariable String id) {
        ResponseDto<ChapterDto> responseDto = new ResponseDto<>();
        chapterService.delete(id);
        return responseDto;
    }
}
