package cn.seu.cose.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.core.SystemContainer;
import cn.seu.cose.view.util.ViewUtil;

public class InitSystemListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/config.xml");
		ViewUtil.CONTEXT_PATH = event.getServletContext().getContextPath();
		SystemContainer.setApplicationContext(applicationContext);

		CategoryCache.init();
	}
}
