package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Chapter;
import com.yinrj.server.domain.ChapterExample;
import com.yinrj.server.dto.ChapterDto;
import com.yinrj.server.dto.ChapterPageDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.ChapterMapper;
import com.yinrj.server.service.ChapterService;
import com.yinrj.server.util.CopyUtil;
import com.yinrj.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public PageDto<ChapterDto> getList(ChapterPageDto<ChapterDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        ChapterExample example = new ChapterExample();
        // 只能够create一次，所以要在if条件外面进行
        ChapterExample.Criteria exampleCriteria = example.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getCourseId())) {
            exampleCriteria.andCourseIdEqualTo(pageDto.getCourseId());
        }
        List<Chapter> chapterList = chapterMapper.selectByExample(example);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);
        pageDto.setData(chapterDtoList);
        return pageDto;
    }

    @Override
    public void save(ChapterDto chapterDto) {
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        if (StringUtils.isEmpty(chapterDto.getId())) {
            String chapterId = addChapter(chapter);
            chapterDto.setId(chapterId);
        } else {
            updateChapter(chapter);
        }
    }

    @Override
    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }


    private String addChapter(Chapter chapter) {
        String chapterId = UuidUtil.getShortUuid();
        chapter.setId(chapterId);
        chapterMapper.insert(chapter);
        return chapterId;
    }

    private void updateChapter(Chapter chapter) {
        chapterMapper.updateByPrimaryKey(chapter);
    }
}
