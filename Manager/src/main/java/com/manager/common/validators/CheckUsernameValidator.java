package com.manager.common.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//@Component
public class CheckUsernameValidator implements ConstraintValidator<CheckUsername, String>{
	@Autowired
	UserDetailsService sc;
	private String message;
	private boolean exist;
	@Override
    public void initialize(final CheckUsername constraintAnnotation) {
        message = constraintAnnotation.message();
        exist = constraintAnnotation.exist();
    }
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValid =true ;
		try {
			sc.loadUserByUsername(value); 
			isValid = exist;
			
		}catch(UsernameNotFoundException e) {
			
			isValid =!exist;
		}
		if(! isValid) {
			context
			.buildConstraintViolationWithTemplate(message)
			.addConstraintViolation()
			.disableDefaultConstraintViolation();
		}
		return isValid;
		
	}

}
