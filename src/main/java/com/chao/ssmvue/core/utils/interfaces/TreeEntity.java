package com.chao.ssmvue.core.utils.interfaces;

import java.util.List;

/**
 * Created by LuZichao on  2018/12/11 11:02
 */
public interface TreeEntity<T,E> {
   T getId();
   T getParentId();
   void setChildList(List<E> childList);
}
