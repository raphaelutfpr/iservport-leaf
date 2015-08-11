package com.iservport.config;

import org.helianto.network.service.RootQueryService;
import org.helianto.seed.AbstractRootContextConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Root configuration.
 * 
 * @author mauriciofernandesdecastro
 */
@ComponentScan(
	basePackages = {
		"org.helianto.*.controller"
		, "org.helianto.*.sender"
		, "com.iservport.*.controller"
		, "com.iservport.*.service"
})
@EnableJpaRepositories(
    basePackages={"org.helianto.*.repository", "com.iservport.*.repository"})
public class RootContextConfig 
	extends AbstractRootContextConfig
{

	/**
	 * Override to set packages to scan.
	 */
	protected String[] getPacakgesToScan() {
		return new String[] {"org.helianto.*.domain", "com.iservport.*.domain"};
	}
	
	@Override
	public RootQueryService rootQueryService() {
		return new RootQueryService(SampleNetworkKeyName.values());
	}
			
}
