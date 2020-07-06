package com.git.myth.gourd.membership.server.mobile.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.git.myth.gourd.membership.server.mobile.innotation.EnableMobileCondition;
import com.git.myth.gourd.membership.server.mobile.sender.MobileSender;
import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorCodeGenerator;
import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorService;
import com.git.myth.gourd.membership.server.mobile.validator.persistance.MobileValidatorPersistance;;

@Component
@Conditional(EnableMobileCondition.class)
public class MobileValidatorServiceImpl implements MobileValidatorService {

	@Autowired
	private MobileValidatorCodeGenerator generator;

	@Autowired
	private MobileValidatorPersistance persistance;

	@Autowired
	private MobileSender sender;

	@Override
	public void sendValidatorCode(final String mobileNumber, final String template) {
		String code = generator.generate();
		String message = String.format(template, code);
		sender.sendMsg(message);
		persistance.save(mobileNumber, code);
	}

	@Override
	public boolean validate(final String mobileNumber, final String validatorCode) {
		if (StringUtils.isEmpty(validatorCode)) {
			return false;
		}
		String storedValidatorCode = persistance.findValidatorCode(mobileNumber);
		if (StringUtils.isEmpty(storedValidatorCode)) {
			return false;
		}
		boolean success = storedValidatorCode.equals(validatorCode);
		if(success)
		{
			persistance.delete(mobileNumber);
		}
		return success;
	}
}