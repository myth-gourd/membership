package com.git.myth.gourd.membership.server.mobile.validator.dto;

public class MobileSendValidationCodeRequest {

	String type;
	
	String mobile;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "MobileSendValidationCodeRequest [type=" + type + ", mobile=" + mobile + "]";
	}
}