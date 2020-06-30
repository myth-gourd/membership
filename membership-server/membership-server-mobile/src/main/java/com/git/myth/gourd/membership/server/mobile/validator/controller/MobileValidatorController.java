package com.git.myth.gourd.membership.server.mobile.validator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorService;
import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorTemplateService;
import com.git.myth.gourd.membership.server.mobile.validator.dto.MobileSendValidationCodeRequest;
import com.git.myth.gourd.membership.server.mobile.validator.dto.MobileValidationRequest;
import com.git.myth.gourd.membership.server.util.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Mobile Validator Api")
@RestController
@RequestMapping(value = "/memebership/mobile/validator/code/")
public class MobileValidatorController 
{
	private static final Logger LOG = LoggerFactory.getLogger(MobileValidatorController.class);
	
	@Autowired
	private MobileValidatorTemplateService templateServ;
	
	@Autowired
	private MobileValidatorService service;
	
	@ApiOperation(value = "Send mobile validation code", httpMethod = "POST")
	@PostMapping(value = "send")
	public Response send(@RequestBody MobileSendValidationCodeRequest request)
	{
		String template= templateServ.getTemplate(request.getType());
		if (StringUtils.isEmpty(template))
		{
			LOG.error(ErrorResponse.UnknownTypeResposne.getMessage());
			return ErrorResponse.UnknownTypeResposne;
		}
		try
		{
			service.sendValidatorCode(request.getMobile(), template);
			LOG.info("send success " + request.toString());
			return Response.SuccessResponse();
		}
		catch(Exception ept)
		{
			LOG.error(ErrorResponse.SendFaildResposne.getMessage() + " exception " + ept.getMessage());
			return ErrorResponse.SendFaildResposne;
		}
	}
	
	@ApiOperation(value = "Validate code", httpMethod = "POST")
	@PostMapping(value = "validate")
	public Response validate(@RequestBody MobileValidationRequest request)
	{
		try
		{
			boolean success = service.validate(request.getMobile(), request.getCode());
			if (success)
			{
				LOG.info("validate success " + request.toString());
				return Response.SuccessResponse();
			}
			else
			{
				LOG.info("validate error " + request.toString());
				return ErrorResponse.ValidatationCodeErrorResposne;
			}
		}
		catch(Exception ept)
		{
			LOG.error("mobile validate exception " + ept.getMessage());
			return ErrorResponse.ValidatationFaildResposne;
		}
	}
	
	private static class ErrorResponse
	{
		private static Response UnknownTypeResposne = new Response("201", "Unknown type");
		private static Response SendFaildResposne = new Response("202", "Send faild");
		private static Response ValidatationCodeErrorResposne = new Response("203", "Validatation code error");
		private static Response ValidatationFaildResposne = new Response("204", "Validation faild");
	}
}