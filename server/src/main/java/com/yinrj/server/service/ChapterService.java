package com.yinrj.server.service;

import com.yinrj.server.domain.Chapter;
import com.yinrj.server.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> getList() {
        return chapterMapper.selectByExample(null);
    }
}
