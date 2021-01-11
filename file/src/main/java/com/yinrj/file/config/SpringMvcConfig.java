package com.yinrj.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yin
 * @date 2021/1/11
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    /**
     * 增加静态资源的配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/f/**").addResourceLocations("file:/Users/yinrongjie/Desktop/file/");
    }
}
