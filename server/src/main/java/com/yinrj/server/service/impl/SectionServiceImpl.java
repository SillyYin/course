package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.Section;
import com.yinrj.server.domain.SectionExample;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.SectionDto;
import com.yinrj.server.dto.SectionPageDto;
import com.yinrj.server.mapper.SectionMapper;
import com.yinrj.server.service.CourseService;
import com.yinrj.server.service.SectionService;
import com.yinrj.server.util.CopyUtil;
import com.yinrj.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource
    private CourseService courseService;

    @Override
    public PageDto<SectionDto> getList(SectionPageDto<SectionDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        SectionExample example = new SectionExample();
        SectionExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getChapterId())) {
            criteria.andChapterIdEqualTo(pageDto.getChapterId());
        }
        if (!StringUtils.isEmpty(pageDto.getCourseId())) {
            criteria.andCourseIdEqualTo(pageDto.getCourseId());
        }
        example.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(example);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);
        pageDto.setData(sectionDtoList);
        return pageDto;
    }

    /**
     * 增加事务的rollbackFor是为了当有非runtimeException的时候也能回滚
     * @param sectionDto
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if (StringUtils.isEmpty(sectionDto.getId())) {
            String sectionId = addSection(section);
            sectionDto.setId(sectionId);
        } else {
            updateSection(section);
        }
        courseService.updateCourseTime(sectionDto.getCourseId());
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