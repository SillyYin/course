package com.yinrj.server.service.impl;

import com.yinrj.server.domain.Test;
import com.yinrj.server.domain.TestExample;
import com.yinrj.server.mapper.TestMapper;
import com.yinrj.server.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestMapper testMapper;

    @Override
    public List<Test> getAllTest() {
        return testMapper.selectByExample(null);
    }

    @Override
    public Test getTestById(String id) {
        TestExample testExample = new TestExample();
        testExample.createCriteria().andIdEqualTo(id);
        return testMapper.selectByExample(testExample).get(0);
    }
}
