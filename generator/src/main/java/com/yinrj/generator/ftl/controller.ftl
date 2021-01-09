package com.yinrj.${module}.controller.admin;

import com.yinrj.server.dto.${Domain}Dto;
import com.yinrj.server.dto.PageDto;
import com.yinrj.server.dto.ResponseDto;
import com.yinrj.server.service.${Domain}Service;
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
public class ${Domain}Controller {
    public static final String BUSINESS_NAME = "${tableNameCn}";

    @Resource
    private ${Domain}Service ${domain}Service;

    @PostMapping("/${domain}/list")
    public ResponseDto<PageDto<${Domain}Dto>> ${domain}List(@RequestBody PageDto<${Domain}Dto> pageDto) {
        ResponseDto<PageDto<${Domain}Dto>> responseDto = new ResponseDto<>();
        responseDto.setContent(${domain}Service.getList(pageDto));
        return responseDto;
    }

    @PostMapping("/${domain}/save")
    public ResponseDto<${Domain}Dto> save(@RequestBody ${Domain}Dto ${domain}Dto) {
        ResponseDto<${Domain}Dto> responseDto = new ResponseDto<>();

        // 保存校验

        ${domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    @DeleteMapping("/${domain}/delete/{id}")
    public ResponseDto<${Domain}Dto> delete(@PathVariable String id) {
        ResponseDto<${Domain}Dto> responseDto = new ResponseDto<>();
        ${domain}Service.delete(id);
        return responseDto;
    }
}
