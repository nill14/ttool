package com.github.nill14.ttool.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class Slf4JInitServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Optionally remove existing handlers attached to j.u.l root logger
		 SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)

		 // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
		 // the initialization phase of your application
		 SLF4JBridgeHandler.install();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
