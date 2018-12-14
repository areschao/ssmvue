package com.chao.ssmvue.core.utils;

import com.chao.ssmvue.core.utils.interfaces.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuZichao on  2018/12/11 11:02
 */
public class TreeUtil {

    public static <T, E extends TreeEntity<T, E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();

        //获取顶层元素集合
        T parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }

        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }

        return resultList;
    }

    /**
     * 获取子数据集合
     */
    private static <T, E extends TreeEntity<T, E>> List<E> getSubList(T id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        T parentId;

        //子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }

        //递归退出条件
        if (childList.size() == 0) {
            return null;
        }

        return childList;
    }
}
