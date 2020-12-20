package com.yinrj.business.controller.admin;

import com.yinrj.server.domain.Chapter;
import com.yinrj.server.domain.Test;
import com.yinrj.server.dto.ChapterDto;
import com.yinrj.server.service.ChapterService;
import com.yinrj.server.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/admin")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/index")
    public String index() {
        return "success";
    }

    @RequestMapping("/chapter/list")
    public List<ChapterDto> chapterList() {
        return chapterService.getList();
    }

}
