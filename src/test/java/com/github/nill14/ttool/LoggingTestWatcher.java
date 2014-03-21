package com.github.nill14.ttool;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTestWatcher extends TestWatcher {
	
	private final Logger log = LoggerFactory.getLogger(LoggingTestWatcher.class);
	
	protected void failed(Throwable e, Description description) {
		log.error("{} failed.", description.getDisplayName(), e);
	}
}
