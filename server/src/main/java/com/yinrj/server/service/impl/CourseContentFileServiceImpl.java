package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.CourseContentFile;
import com.yinrj.server.domain.CourseContentFileExample;
import com.yinrj.server.dto.CourseContentFileDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.CourseContentFileMapper;
import com.yinrj.server.service.CourseContentFileService;
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
public class CourseContentFileServiceImpl implements CourseContentFileService {
    @Resource
    private CourseContentFileMapper courseContentFileMapper;

    @Override
    public PageDto<CourseContentFileDto> getList(PageDto<CourseContentFileDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<CourseContentFile> courseContentFileList = courseContentFileMapper.selectByExample(null);
        PageInfo<CourseContentFile> pageInfo = new PageInfo<>(courseContentFileList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<CourseContentFileDto> courseContentFileDtoList = CopyUtil.copyList(courseContentFileList, CourseContentFileDto.class);
        pageDto.setData(courseContentFileDtoList);
        return pageDto;
    }

    @Override
    public void save(CourseContentFileDto courseContentFileDto) {
        CourseContentFile courseContentFile = CopyUtil.copy(courseContentFileDto, CourseContentFile.class);
        if (StringUtils.isEmpty(courseContentFileDto.getId())) {
            String courseContentFileId = addCourseContentFile(courseContentFile);
            courseContentFileDto.setId(courseContentFileId);
        } else {
            updateCourseContentFile(courseContentFile);
        }
    }

    @Override
    public void delete(String id) {
        courseContentFileMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CourseContentFileDto> list(String courseId) {
        CourseContentFileExample example = new CourseContentFileExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseContentFile> courseContentFileList = courseContentFileMapper.selectByExample(example);
        List<CourseContentFileDto> courseContentFileDtos;
        courseContentFileDtos = CopyUtil.copyList(courseContentFileList, CourseContentFileDto.class);
        return courseContentFileDtos;
    }


    private String addCourseContentFile(CourseContentFile courseContentFile) {
        String courseContentFileId = UuidUtil.getShortUuid();
        courseContentFile.setId(courseContentFileId);
        courseContentFileMapper.insert(courseContentFile);
        return courseContentFileId;
    }

    private void updateCourseContentFile(CourseContentFile courseContentFile) {
        courseContentFileMapper.updateByPrimaryKey(courseContentFile);
    }
}