package com.git.myth.gourd.membership.server.mobile.innotation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import com.git.myth.gourd.membership.server.mobile.validator.configuration.MobileConfiguration;
import com.git.myth.gourd.membership.server.mobile.validator.controller.MobileValidatorController;
import com.git.myth.gourd.membership.server.mobile.validator.persistance.redis.MobileValidatorRedisPersistance;
import com.git.myth.gourd.membership.server.mobile.validator.persistance.redis.MobileValidatorRedisPool;
import com.git.myth.gourd.membership.server.util.AnnotationReflectUtil;

public class ImportMobileValidatorSelector implements ImportSelector 
{
	private static final Logger LOG = LoggerFactory.getLogger(ImportMobileValidatorSelector.class);
	
	private static final String ENABLE_API_NAME = "enableApi";
	
	private static final String CODE_GENERATOR_CLASS_NAME = "codeGeneratorClass";
	
	private static final String PERSISTANCE_CLASS_NAME = "persistanceClass";
	
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {

		boolean enableMobile = MobileConfiguration.isEnableMobile(importingClassMetadata);
		if (enableMobile)
		{
			LOG.info("Membsership is enableMobile");
			MultiValueMap<String, Object> mobileAttrs = importingClassMetadata.getAllAnnotationAttributes(MembershipMobile.class.getName(), false);
			if (mobileAttrs == null)
			{
				Class<?> codeGeneratorClass = AnnotationReflectUtil.findDeclaredPropertyDefault(MembershipMobile.class, CODE_GENERATOR_CLASS_NAME);
				Boolean enableApi = AnnotationReflectUtil.findDeclaredPropertyDefault(MembershipMobile.class, ENABLE_API_NAME);
				Class<?> persistanceClass = AnnotationReflectUtil.findDeclaredPropertyDefault(MembershipMobile.class, PERSISTANCE_CLASS_NAME);
				LOG.info("MembMobile load default configutation");
				return returnBeanList(codeGeneratorClass, persistanceClass, enableApi);
			}
			else
			{
				Class<?> codeGeneratorClass = (Class<?>) mobileAttrs.get(CODE_GENERATOR_CLASS_NAME).get(0);
				LOG.info("MembMobile load codeGeneratorClass:" + codeGeneratorClass.getName());
				Class<?> persistanceClass = (Class<?>) mobileAttrs.get(PERSISTANCE_CLASS_NAME).get(0);
				LOG.info("MembMobile load persistanceClass:" + persistanceClass.getName());
				boolean enableApi = (Boolean)mobileAttrs.get(ENABLE_API_NAME).get(0);
				LOG.info("MembMobile enableApi :" + String.valueOf(enableApi));
				return returnBeanList(codeGeneratorClass, persistanceClass, enableApi);
			}
		}
		LOG.info("Membsership is not enableMobile");
		return new String[]{};
	}
	
	private String[] returnBeanList(Class<?> codeGeneratorClass, Class<?> persistanceClass, boolean enableApi)
	{
		List<String> list = new ArrayList<String>();
		list.add(codeGeneratorClass.getName());
		list.add(persistanceClass.getName());
		if (MobileValidatorRedisPersistance.class.getName().equals(persistanceClass.getName()))
		{
			list.add(MobileValidatorRedisPool.class.getName());
		}
		if (enableApi)
		{
			list.add(MobileValidatorController.class.getName());
		}
		return list.toArray(new String[]{});
	}
}