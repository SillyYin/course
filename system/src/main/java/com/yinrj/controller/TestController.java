package com.yinrj.controller;

import com.yinrj.server.domain.Test;
import com.yinrj.server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/13
 */
@RestController
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

    @RequestMapping("/list")
    public List<Test> testList() {
        return testService.getAllTest();
    }

    @RequestMapping("/getTest")
    public Test getTest() {
        return testService.getTestById("3");
    }
}
