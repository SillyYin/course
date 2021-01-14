package com.yinrj.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrj.server.domain.File;
import com.yinrj.server.dto.FileDto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.mapper.FileMapper;
import com.yinrj.server.service.FileService;
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
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;

    @Override
    public PageDto<FileDto> getList(PageDto<FileDto> pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<File> fileList = fileMapper.selectByExample(null);
        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        pageDto.setTotalCount(pageInfo.getTotal());
        List<FileDto> fileDtoList = CopyUtil.copyList(fileList, FileDto.class);
        pageDto.setData(fileDtoList);
        return pageDto;
    }

    @Override
    public void save(FileDto fileDto) {
        File file = CopyUtil.copy(fileDto, File.class);
        if (StringUtils.isEmpty(fileDto.getId())) {
            String fileId = addFile(file);
            fileDto.setId(fileId);
        } else {
            updateFile(file);
        }
    }

    @Override
    public void delete(String id) {
        fileMapper.deleteByPrimaryKey(id);
    }


    private String addFile(File file) {
        String fileId = UuidUtil.getShortUuid();
        file.setId(fileId);
        fileMapper.insert(file);
        return fileId;
    }

    private void updateFile(File file) {
        fileMapper.updateByPrimaryKey(file);
    }
}