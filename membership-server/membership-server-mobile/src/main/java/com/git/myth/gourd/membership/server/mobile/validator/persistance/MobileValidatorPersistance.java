package com.git.myth.gourd.membership.server.mobile.validator.persistance;

public interface MobileValidatorPersistance 
{
	void save(String mobileNumber, String validatorCode);
	
	void delete(String mobileNumber);
	
	String findValidatorCode(String mobileNumber);
}