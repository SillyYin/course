package com.yinrj.business.controller;

import com.yinrj.server.domain.Chapter;
import com.yinrj.server.domain.Test;
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
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @Resource
    private TestService testService;

    @RequestMapping("/index")
    public String index() {
        return "success";
    }

    @RequestMapping("/list")
    public List<Chapter> chapterList() {
        return chapterService.getList();
    }

    @RequestMapping("/test")
    public List<Test> getList() {
        return testService.getAllTest();
    }
}
