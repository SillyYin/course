package com.yinrj.server.service;

import com.yinrj.server.dto.SectionDto;
import com.yinrj.server.dto.PageDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface SectionService {
    PageDto<SectionDto> getList(PageDto<SectionDto> pageDto);

    void save(SectionDto sectionDto);

    void delete(String id);
}
