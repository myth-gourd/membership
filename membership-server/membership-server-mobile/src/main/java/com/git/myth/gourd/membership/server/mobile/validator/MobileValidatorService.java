package com.git.myth.gourd.membership.server.mobile.validator;

public interface MobileValidatorService 
{
	void sendValidatorCode(String mobileNumber, String template);
	
	boolean validate(String mobileNumber, String validatorCode);
}