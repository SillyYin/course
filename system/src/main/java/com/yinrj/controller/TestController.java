package com.yinrj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yin
 * @date 2020/12/13
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
