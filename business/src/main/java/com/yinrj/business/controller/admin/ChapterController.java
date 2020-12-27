package com.yinrj.business.controller.admin;

import com.yinrj.server.dto.ChapterDto;
import com.yinrj.server.dto.PageDto;
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

    @RequestMapping("/index")
    public String index() {
        return "success";
    }

    @RequestMapping("/chapter/list")
    public PageDto<ChapterDto> chapterList(@RequestBody PageDto<ChapterDto> pageDto) {
        log.info("pageDto: {}", pageDto);
        return chapterService.getList(pageDto);
    }

    @RequestMapping("/chapter/save")
    public ChapterDto save(@RequestBody ChapterDto chapterDto) {
        log.info("[save]chapterDto: {}", chapterDto);
        chapterService.addChapter(chapterDto);
        return chapterDto;
    }

}
