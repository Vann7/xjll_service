package com.van.Utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SwitchUtils {

    /**
     * List转换
     * @param sourceList
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T,K> List<T> toList(List<K> sourceList, Class<T> tClass) {
        T target = null;
        List<T> targetList = new ArrayList<>();
        for(Object source : sourceList) {
            sourceList.getClass();
            try {
                target = tClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }
        return targetList;
    }


    /**
     * Bean转换
     * @param source
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T,K> T toBean(K source, Class<T> tClass) {
        T target = null;
        try {
            target = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
