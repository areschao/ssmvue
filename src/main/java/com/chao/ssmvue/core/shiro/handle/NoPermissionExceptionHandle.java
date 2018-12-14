package com.chao.ssmvue.core.shiro.handle;

import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by LuZichao on  2018/12/11 10:12
 */
@RestControllerAdvice
@Slf4j
public class NoPermissionExceptionHandle {

    @ExceptionHandler(value = AuthorizationException.class)
    public HttpResult handle(AuthorizationException e) {
        log.error("出现无权限访问");
        return HttpResultUtil.createNoPermissionResult();
    }

}
