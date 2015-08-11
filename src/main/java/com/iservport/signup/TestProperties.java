package com.iservport.signup;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

	/**
	 * Entity properties.
	 * 
	 * @author mauriciofernandesdecastro
	 */
	@Component
	public class TestProperties {

	    private final String defaultAlias;
	    
	    private final String rootPrincipal;
	    
	    private final String rootFirstName;
	    
	    private final String rootLastName;
	    
	    private final String initialSecret;
	 
	    /**
	     * Constructor.
	     */
	    @Inject
	    public TestProperties(Environment env) {
			this.defaultAlias = env.getProperty("iservport.defaultAlias", "iservport.com.br");
			this.rootPrincipal = env.getProperty("iservport.rootPrincipal", "mariane@iservport.com");
			this.rootFirstName = env.getProperty("iservport.rootFirstName", "Mariane");
			this.rootLastName = env.getProperty("iservport.rootLastName", "Affonso Medeiros");
			this.initialSecret = env.getProperty("iservport.initialSecret", "123456");
	    }

		public String getDefaultAlias() {
			return defaultAlias;
		}

		public String getRootPrincipal() {
			return rootPrincipal;
		}

		public String getRootFirstName() {
			return rootFirstName;
		}

		public String getRootLastName() {
			return rootLastName;
		}
	    
		public String getInitialSecret() {
			return initialSecret;
		}
}
