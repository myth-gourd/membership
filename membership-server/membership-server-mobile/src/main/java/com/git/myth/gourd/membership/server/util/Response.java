package com.git.myth.gourd.membership.server.util;

public class Response 
{
	private boolean success;
	
	private String status;
	
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Response(boolean success, String status, String message)
	{
		this.success = success;
		this.status = status;
		this.message = message;
	}
	
	public Response(String status, String message)
	{
		this.success = false;
		this.status = status;
		this.message = message;
	}
	
	public static Response SuccessResponse(){
		return SuccessResponse.instance;
	}
	
	private static class SuccessResponse{
		 private static Response instance = new Response(true,"200","");
	}
}