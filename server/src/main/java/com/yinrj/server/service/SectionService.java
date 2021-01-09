package com.yinrj.server.service;

import com.yinrj.server.dto.SectionDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.SectionPageDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface SectionService {
    PageDto<SectionDto> getList(SectionPageDto<SectionDto> pageDto);

    void save(SectionDto sectionDto);

    void delete(String id);
}
