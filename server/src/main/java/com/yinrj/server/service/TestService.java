package com.yinrj.server.service;

import com.yinrj.server.domain.Test;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
@Service
public interface TestService {
    List<Test> getAllTest();
    Test getTestById(String id);
}
