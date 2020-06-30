package com.git.myth.gourd.membership.server.mobile.validator.dto;

public class MobileValidationRequest
{
	String mobile;
	
	String code;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MobileValidationRequest [mobile=" + mobile + ", code=" + code + "]";
	}
}