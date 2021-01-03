package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.${Domain};
import com.yinrj.server.dto.${Domain}Dto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.${Domain}Mapper;
import com.yinrj.server.service.${Domain}Service;
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
public class ${Domain}ServiceImpl implements ${Domain}Service {
    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    @Override
    public PageDto<${Domain}Dto> getList(PageDto<${Domain}Dto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(null);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<${Domain}Dto> ${domain}DtoList = CopyUtil.copyList(${domain}List, ${Domain}Dto.class);
        pageDto.setData(${domain}DtoList);
        return pageDto;
    }

    @Override
    public void save(${Domain}Dto ${domain}Dto) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        if (StringUtils.isEmpty(${domain}Dto.getId())) {
            String ${domain}Id = add${Domain}(${domain});
            ${domain}Dto.setId(${domain}Id);
        } else {
            update${Domain}(${domain});
        }
    }

    @Override
    public void delete(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }


    private String add${Domain}(${Domain} ${domain}) {
        String ${domain}Id = UuidUtil.getShortUuid();
        ${domain}.setId(${domain}Id);
        ${domain}Mapper.insert(${domain});
        return ${domain}Id;
    }

    private void update${Domain}(${Domain} ${domain}) {
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }
}