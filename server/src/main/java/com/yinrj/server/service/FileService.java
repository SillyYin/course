package com.yinrj.server.service;

import com.yinrj.server.dto.FileDto;
import com.yinrj.server.dto.PageDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface FileService {
    PageDto<FileDto> getList(PageDto<FileDto> pageDto);

    void save(FileDto fileDto);

    void delete(String id);
}
