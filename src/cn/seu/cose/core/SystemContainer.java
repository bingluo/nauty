package cn.seu.cose.core;

import org.springframework.context.ApplicationContext;

public class SystemContainer {
	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	public static Object lookup(String beanId) {
		Object bean = null;
		bean = applicationContext.getBean(beanId);
		return bean;
	}
}
