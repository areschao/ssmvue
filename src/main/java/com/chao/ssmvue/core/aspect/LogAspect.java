package com.chao.ssmvue.core.aspect;


import com.chao.ssmvue.base.security.entity.Log;
import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.base.security.service.LogService;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.exception.NotFoundCurrentUserException;
import com.chao.ssmvue.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Enumeration;

/**
 * Created by LuZichao on  2018/12/12 9:27
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("execution(public * com.chao.ssmvue.base.security.controller..*.*(..))")
    public void security() {
    }

    //    @Pointcut("execution(public * com.chao.ssmvue.base.business.controller..*.*(..))")
    public void business() {
    }


    @Pointcut("security()")
    public void init() {
    }

    @Around("init()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object object = null;
        //检测用户登录
        User user = getCurrentUser();
        if (user == null) {
            throw new NotFoundCurrentUserException();
        }

        //执行内部方法
        object = point.proceed();

        String strMethodName = point.getSignature().getName();
        String strClassName = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();
        StringBuffer bfParams = new StringBuffer();
        Enumeration<String> paraNames = null;
        HttpServletRequest request = null;
        if (params != null && params.length > 0) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            paraNames = request.getParameterNames();
            String key;
            String value;
            while (paraNames.hasMoreElements()) {
                key = paraNames.nextElement();
                value = request.getParameter(key);
                bfParams.append(key).append("=").append(value).append("&");
            }
            if (StringUtil.isBlank(bfParams)) {
                bfParams.append(request.getQueryString());
            }
        }

        String strMessage = String
                .format("[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName, bfParams.toString());
        log.info(strMessage);
        try {
            Log logg = new Log();
            logg.setLoginName(user.getLoginName());
            logg.setRoleName(user.getName());
            logg.setOptContent(strMessage);
            if (request != null) {
                logg.setClientIp(request.getRemoteAddr());
            }
            if (object instanceof HttpResult) {
                BeanUtils.copyProperties(object, logg);
            }
            log.info(logg.toString());
            logService.save(logg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return object;
    }

    private User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }


}
