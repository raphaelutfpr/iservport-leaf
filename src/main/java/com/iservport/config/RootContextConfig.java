package com.iservport.config;

import org.helianto.config.AbstractRootContextConfig;
import org.helianto.core.config.HeliantoServiceConfig;
import org.helianto.qualifier.QualifierAdapterList;
import org.helianto.qualifier.SimpleNetworkQualifierList;
import org.helianto.qualifier.SimpleUserQualifierList;
import org.helianto.sendgrid.config.SendGridConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Root configuration.
 * 
 * @author mauriciofernandesdecastro
 */
@Configuration
@Import({HeliantoServiceConfig.class, SendGridConfig.class})
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
		
		@Override @Bean
		public QualifierAdapterList networkQualifierAdapterList() {
			System.out.println("   Network");
			return new SimpleNetworkQualifierList();
		}

		@Override @Bean
		public QualifierAdapterList userQualifierAdapterList() {
			System.out.println("   User");
			return new SimpleUserQualifierList();
		}
		
}
