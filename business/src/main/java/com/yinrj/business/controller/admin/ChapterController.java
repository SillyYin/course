package com.yinrj.business.controller.admin;

import com.yinrj.server.dto.ChapterDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Yin
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter/list")
    public ResponseDto<PageDto<ChapterDto>> chapterList(@RequestBody PageDto<ChapterDto> pageDto) {
        log.info("pageDto: {}", pageDto);
        ResponseDto<PageDto<ChapterDto>> responseDto = new ResponseDto<>();
        responseDto.setContent(chapterService.getList(pageDto));
        return responseDto;
    }

    @RequestMapping("/chapter/save")
    public ResponseDto<ChapterDto> save(@RequestBody ChapterDto chapterDto) {
        log.info("[save]chapterDto: {}", chapterDto);
        ResponseDto<ChapterDto> responseDto = new ResponseDto<>();
        chapterService.addChapter(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

}
