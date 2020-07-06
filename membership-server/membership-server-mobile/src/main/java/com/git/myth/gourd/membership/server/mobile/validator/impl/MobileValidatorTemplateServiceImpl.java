package com.git.myth.gourd.membership.server.mobile.validator.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorTemplate;
import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorTemplateService;
import com.git.myth.gourd.membership.server.mobile.validator.configuration.MobileValidatorConfiguration;
import com.git.myth.gourd.membership.server.mobile.innotation.EnableMobileCondition;

@Component
@Conditional(EnableMobileCondition.class)
public class MobileValidatorTemplateServiceImpl implements MobileValidatorTemplateService {

	private static final Logger LOG = LoggerFactory.getLogger(MobileValidatorTemplateServiceImpl.class);
	
	private final Map<String, String> ALL_TEMPLATE_MAP = new HashMap<String, String>();
	
	@Autowired
	private MobileValidatorConfiguration confiuration;

	@PostConstruct
	private void init(){
		if (confiuration.getTemplates() == null)
		{
			LOG.warn("Not config templates", NullPointerException.class);
		}
		for(MobileValidatorTemplate template : confiuration.getTemplates())
		{
			if (!ALL_TEMPLATE_MAP.containsKey(template.getKey()))
			{
				ALL_TEMPLATE_MAP.put(template.getKey(), template.getValue());
			}
		}
	}
	
	@Override
	public String getTemplate(String type) {
		return ALL_TEMPLATE_MAP.get(type);
	}
}