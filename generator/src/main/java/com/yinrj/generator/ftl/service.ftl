package com.yinrj.server.service;

import com.yinrj.server.dto.${Domain}Dto;
import com.yinrj.server.dto.PageDto;

/**
 * @author: Yin
 * @date: 2020/12/19
 */
public interface ${Domain}Service {
    PageDto<${Domain}Dto> getList(PageDto<${Domain}Dto> pageDto);

    void save(${Domain}Dto ${domain}Dto);

    void delete(String id);
}
