package com.yinrj.generator.server;

import com.yinrj.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yin
 * @date 2021/1/3
 */
public class ServerGenerator {

    public static final String SERVICE_PATH = "server/src/main/java/com/yinrj/server/service/";
    public static final String SERVICE_IMPL_PATH = "server/src/main/java/com/yinrj/server/service/impl/";

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);

        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(SERVICE_PATH + Domain + "Service.java", map);

        FreemarkerUtil.initConfig("serviceimpl.ftl");
        FreemarkerUtil.generator(SERVICE_IMPL_PATH + Domain + "ServiceImpl.java", map);
    }
}
