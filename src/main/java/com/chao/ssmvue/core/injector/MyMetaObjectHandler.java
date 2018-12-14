package com.chao.ssmvue.core.injector;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chao.ssmvue.core.utils.DateUtil;
import com.chao.ssmvue.core.utils.LocalDateUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自定义公共字段填充处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private final String createTime = "createTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        setFileVal(createTime,LocalDateUtil.getCurrentLocalDateTime(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }

    private void setFileVal(String fileName , Object fileVal , MetaObject metaObject){
        //获取到需要被填充的字段的值
        Object obj = getFieldValByName(fileName,metaObject);
        if (obj == null){
            setFieldValByName(fileName, fileVal,metaObject);
        }
    }
}
