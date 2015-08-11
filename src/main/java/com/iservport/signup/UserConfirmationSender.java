package com.iservport.signup;

import javax.inject.Inject;

import org.helianto.sendgrid.message.sender.AbstractTemplateSender;
import org.springframework.beans.factory.annotation.Value;

public class UserConfirmationSender extends AbstractTemplateSender{

	@Value("${sender.staticRedirectQuestion}")
	private String staticRedirectQuestion;
	
	@Value("${sender.staticRedirectMessage}")
	private String staticRedirectMessage;

	/**
	 * Constructor.
	 * 
	 * @param entityProps
	 */
	@Inject
	public UserConfirmationSender(TestProperties entityProps) {
		super(entityProps.getRootPrincipal(), entityProps.getRootFirstName(), "userConfirmation");
	}
	
	protected String getConfirmationUri(String... params) {
		return getApiUrl()+"/signup/verify?token=x";
	}
}
