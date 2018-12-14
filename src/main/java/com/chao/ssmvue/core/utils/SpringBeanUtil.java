package com.chao.ssmvue.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringBeanUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	// 打印所有的bean
	public static void printAllBean() {
    	String[] beans = applicationContext.getBeanDefinitionNames();
		for (String beanName : beans) {
			System.out.println("beanName: "+ beanName);
		}
    }

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		setLocalApplicationContext(ctx);
	}

	private static void setLocalApplicationContext(ApplicationContext ctx) {
		applicationContext = ctx;
	}

	public static Object getBean(String beanName) {
		Object obj = applicationContext.getBean(beanName);
		return throwNullOrReturnObject(obj);
	}

	public static <T> T getBean(Class<T> beanClass) {
		T bean = applicationContext.getBean(beanClass);
		return throwNullOrReturnObject(bean);
	}

	private static <T> T throwNullOrReturnObject(T bean) {
		if (bean == null) {
			// TODO 抛出异常以后修复
			throw new NullPointerException("没有发现Bean异常");
		}
		return bean;
	}

	public static Map<String, ?> getBeansByType(Class<?> type) {
		return applicationContext.getBeansOfType(type);
	}

}
