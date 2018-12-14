package com.chao.ssmvue.core.controller;

import com.chao.ssmvue.core.annotation.ApiDesc;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by LuZichao on  2018/12/7 9:09
 * 反射测试  用于拿到方法描述和权限信息
 */
@Component
@Slf4j
public class PermissionController implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.debug("服务启动中<<<<>>>>加载权限信息");

        Map<String, Object> permissionMap = new HashMap<>();
        Reflections reflections = new Reflections("com.chao.ssmvue.base.security.controller", new MethodAnnotationsScanner());

        Set<Method> apiDescSet = reflections.getMethodsAnnotatedWith(ApiDesc.class);
        Set<Method> newApiDescSet = apiDescSet.stream().filter(t ->
                t.isAnnotationPresent(GetMapping.class) ||
                        t.isAnnotationPresent(PostMapping.class) ||
                        t.isAnnotationPresent(DeleteMapping.class) ||
                        t.isAnnotationPresent(PutMapping.class)
        ).collect(Collectors.toSet());
        System.out.println(newApiDescSet);
        newApiDescSet.forEach(t -> {
            String apiPath = null;
            if (t.isAnnotationPresent(PostMapping.class)) {
                Class<?> declaringClass = t.getDeclaringClass();
                String[] path = declaringClass.getAnnotation(RequestMapping.class).value();
                String[] path1 = t.getAnnotation(PostMapping.class).value();
                if (path1.length == 0){
                    apiPath = path[0];
                }else {
                    apiPath = path[0] + path1[0];
                }
            }
            if (t.isAnnotationPresent(GetMapping.class)) {
                Class<?> declaringClass = t.getDeclaringClass();
                String[] path = declaringClass.getAnnotation(RequestMapping.class).value();
                String[] path1 = t.getAnnotation(GetMapping.class).value();
                if (path1.length == 0){
                    apiPath = path[0];
                }else {
                    apiPath = path[0] + path1[0];
                }
            }
            if (t.isAnnotationPresent(DeleteMapping.class)) {
                Class<?> declaringClass = t.getDeclaringClass();
                String[] path = declaringClass.getAnnotation(RequestMapping.class).value();
                String[] path1 = t.getAnnotation(DeleteMapping.class).value();
                if (path1.length == 0){
                    apiPath = path[0];
                }else {
                    apiPath = path[0] + path1[0];
                }
            }
            if (t.isAnnotationPresent(PutMapping.class)) {
                Class<?> declaringClass = t.getDeclaringClass();
                String[] path = declaringClass.getAnnotation(RequestMapping.class).value();
                String[] path1 = t.getAnnotation(PutMapping.class).value();
                if (path1.length == 0){
                    apiPath = path[0];
                }else {
                    apiPath = path[0] + path1[0];
                }
            }
            permissionMap.put(t.getAnnotation(ApiDesc.class).value(), apiPath);
        });

        System.out.println(permissionMap);

    }
}
