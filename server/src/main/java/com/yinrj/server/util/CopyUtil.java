package com.yinrj.server.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yin
 * @date 2020/12/27
 */
public class CopyUtil {
    /**
     * 列表内容进行属性拷贝
     * @param source
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List source, Class<T> tClass) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)) {
            for (Object c : source) {
                T obj = copy(c, tClass);
                target.add(obj);
            }
        }
        return target;
    }
    /**
     * 封装单个的属性拷贝
     * @param source
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, Class<T> tClass) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }
}
