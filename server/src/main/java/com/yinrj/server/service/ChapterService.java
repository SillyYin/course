package com.yinrj.server.service;

import com.yinrj.server.dto.ChapterDto;
import com.yinrj.server.dto.PageDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface ChapterService {
    PageDto<ChapterDto> getList(PageDto<ChapterDto> pageDto);

    void addChapter(ChapterDto chapterDto);
}
