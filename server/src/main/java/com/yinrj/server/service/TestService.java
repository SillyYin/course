package com.yinrj.server.service;

import com.yinrj.server.domain.Test;

import java.util.List;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface TestService {
    List<Test> getAllTest();
    Test getTestById(String id);
}
