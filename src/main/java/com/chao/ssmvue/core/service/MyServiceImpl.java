package com.chao.ssmvue.core.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 公共继承service 类
 * 为了重写 或者添加方法
 * @param <M>
 * @param <T>
 */
public class MyServiceImpl<M extends BaseMapper<T> , T> extends ServiceImpl<M,T> {


}
