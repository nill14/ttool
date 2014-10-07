// ******************************************************************
//                                                                 
//  ManagedViewHelper.java                                               
//  Copyright 2012 PSI AG. All rights reserved.              
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//                                                                 
// ******************************************************************

package com.github.nill14.ttool.sandbox;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

public class UnmanagedComponentHelper {
	
	@Inject
	private ApplicationContext applicationContext; 
	
	public void autowireBeanProperties(Object bean)
	{
		applicationContext.getAutowireCapableBeanFactory().autowireBeanProperties(
			    bean, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
	}
}


