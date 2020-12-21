package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Chapter;
import com.yinrj.server.dto.ChapterDto;
import com.yinrj.server.dto.PageDto;
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

    public PageDto<ChapterDto> getList(PageDto<ChapterDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<Chapter> chapterList = chapterMapper.selectByExample(null);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (Chapter chapter : chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        pageDto.setData(chapterDtoList);
        return pageDto;
    }
}
