package com.yinrj.business.controller.admin;

import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.dto.SectionDto;
import com.yinrj.server.service.SectionService;
import com.yinrj.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Yin
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class SectionController {
    public static final String BUSINESS_NAME = "小节";

    @Resource
    private SectionService sectionService;

    @PostMapping("/section/list")
    public ResponseDto<PageDto<SectionDto>> sectionList(@RequestBody PageDto<SectionDto> pageDto) {
        ResponseDto<PageDto<SectionDto>> responseDto = new ResponseDto<>();
        responseDto.setContent(sectionService.getList(pageDto));
        return responseDto;
    }

    @PostMapping("/section/save")
    public ResponseDto<SectionDto> save(@RequestBody SectionDto sectionDto) {
        ResponseDto<SectionDto> responseDto = new ResponseDto<>();

        // 保存校验
        ValidatorUtil.require(sectionDto.getTitle(), "标题");
        ValidatorUtil.length(sectionDto.getTitle(), "标题", 1, 50);
        ValidatorUtil.length(sectionDto.getVideo(), "视频", 1, 200);

        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    @DeleteMapping("/section/delete/{id}")
    public ResponseDto<SectionDto> delete(@PathVariable String id) {
        ResponseDto<SectionDto> responseDto = new ResponseDto<>();
        sectionService.delete(id);
        return responseDto;
    }
}
