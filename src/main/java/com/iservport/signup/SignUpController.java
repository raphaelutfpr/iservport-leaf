package com.iservport.signup;

import javax.inject.Inject;

import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Signup;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.security.controller.AbstractSignUpController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController extends AbstractSignUpController{

	@Inject 
	private UserConfirmationSender userConfirmationSender;

	@Inject
	private IdentityRepository identityRepository;
	
	@Override
	protected String sendConfirmation(Signup signup) {
		Identity identity = identityRepository.findByPrincipal(signup.getPrincipal());
		
		if (userConfirmationSender.send(identity, "", encryptToken(identity))) {
			return "true";
		}
		return "false";
	}

}
