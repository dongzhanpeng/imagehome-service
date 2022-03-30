package com.fly.imagehome.utils;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DozerConvertorUtils
 * @Description dozer转换器
 * @Date 2021/12/8 13:42
 * @Author Fly
 * @Version 1.0
 */
public class DozerConvertorUtil {

    private static final DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * List  实体类 转换器
     *
     * @param source 原数据
     * @param clz    转换类型
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> convertor(List<S> source, Class<T> clz) {
        if (source == null) return null;
        List<T> map = new ArrayList<>();
        for (S s : source) {
            map.add(mapper.map(s, clz));
        }
        return map;
    }

    /**
     * 实体类 深度转换器
     *
     * @param source
     * @param clz
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> T convertor(S source, Class<T> clz) {
        if (source == null) return null;
        return mapper.map(source, clz);
    }

    public static void convertor(Object source, Object object) {
        mapper.map(source, object);
    }

    public static <T> void copyConvertor(T source, Object object) {
        mapper.map(source, object);
    }
}