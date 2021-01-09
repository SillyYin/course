package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Section;
import com.yinrj.server.domain.SectionExample;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.SectionDto;
import com.yinrj.server.mapper.SectionMapper;
import com.yinrj.server.service.SectionService;
import com.yinrj.server.util.CopyUtil;
import com.yinrj.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/19
 */
@Service
public class SectionServiceImpl implements SectionService {
    @Resource
    private SectionMapper sectionMapper;

    @Override
    public PageDto<SectionDto> getList(PageDto<SectionDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        SectionExample example = new SectionExample();
        example.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(example);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);
        pageDto.setData(sectionDtoList);
        return pageDto;
    }

    @Override
    public void save(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if (StringUtils.isEmpty(sectionDto.getId())) {
            String sectionId = addSection(section);
            sectionDto.setId(sectionId);
        } else {
            updateSection(section);
        }
    }

    @Override
    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }


    private String addSection(Section section) {
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        String sectionId = UuidUtil.getShortUuid();
        section.setId(sectionId);
        sectionMapper.insert(section);
        return sectionId;
    }

    private void updateSection(Section section) {
        Section selectedSection = sectionMapper.selectByPrimaryKey(section.getId());
        section.setCreatedAt(selectedSection.getCreatedAt());
        section.setUpdatedAt(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }
}