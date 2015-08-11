package com.iservport.config;

import org.helianto.seed.AbstractDispatcherServletConfig;
import org.helianto.seed.AbstractServletContextConfig;

/**
 * Simple servlet config.
 * 
 * @author mauriciofernandesdecastro
 */
public class DispatcherServletConfig 
	extends AbstractDispatcherServletConfig
{

	@Override
	protected Class<? extends AbstractServletContextConfig> getServletContextConfigClass() {
		return ServletContextConfig.class;
	}

}
