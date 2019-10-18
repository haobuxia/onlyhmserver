package com.tianyi.helmet.server.util;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 关联属性相关
 * <p>
 * Created by liuhanc on 2017/12/18.
 */
public class RelationUtils {

    /**
     * 根据一个list中的元素的属性，自动为另外一个属性赋值。
     *
     * @param origList       原始列表
     * @param origIdGetter   原始列表中需要翻译的属性的get方法
     * @param transListFunc  根据1个List的id
     * @param destIdGetter   id对应对象的id属性的get方法
     * @param destNameGetter id对应对象的name属性的get方法
     * @param origNameSetter 原始列表中元素的name属性的set方法
     * @param <T1> 原始列表中元素类型
     * @param <T2> 翻译对象类型
     * @param <INT> ID属性的类型
     * @param <STR> Name属性的类型
     */
    public static <T1, T2, INT, STR> void fullfilListRelateProperty(
            List<T1> origList,
            Function<T1, INT> origIdGetter,
            Function<List<INT>, List<T2>> transListFunc,
            Function<T2, INT> destIdGetter,
            Function<T2, STR> destNameGetter,
            BiConsumer<T1, STR> origNameSetter) {
        if (CollectionUtils.isEmpty(origList)) return;

        List<INT> idList = origList.stream().map(origIdGetter).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(idList)) return;

        List<T2> destList = transListFunc.apply(idList);
        if (CollectionUtils.isEmpty(destList)) return;

        Map<INT, STR> userMap = destList.stream().collect(Collectors.toMap(destIdGetter, destNameGetter));

        origList.stream().forEach(t1 -> {
            INT userId = origIdGetter.apply(t1);
            STR userName = userMap.get(userId);
            origNameSetter.accept(t1, userName);
        });
    }
}
