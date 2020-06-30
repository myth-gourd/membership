package com.git.myth.gourd.membership.server.mobile.validator.impl;

import java.util.Random;

import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorCodeGenerator;

public class MobileValidatorCodeGeneratorImpl implements MobileValidatorCodeGenerator{

	public static Random RANDOM = new Random();
	
	@Override
	public String generate() {
		int num1 = RANDOM.nextInt(10);
		int num2 = RANDOM.nextInt(10);
		int num3 = RANDOM.nextInt(10);
		int num4 = RANDOM.nextInt(10);
		return String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3) + String.valueOf(num4);
	}
}