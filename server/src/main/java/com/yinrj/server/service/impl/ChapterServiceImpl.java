package com.yinrj.server.service.impl;

import com.yinrj.server.domain.Chapter;
import com.yinrj.server.dto.ChapterDto;
import com.yinrj.server.mapper.ChapterMapper;
import com.yinrj.server.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> getList() {
        List<Chapter> chapterList = chapterMapper.selectByExample(null);
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (Chapter chapter : chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
