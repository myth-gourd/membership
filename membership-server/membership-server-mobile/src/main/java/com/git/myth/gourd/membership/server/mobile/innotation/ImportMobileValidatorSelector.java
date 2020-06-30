package com.git.myth.gourd.membership.server.mobile.innotation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import com.git.myth.gourd.membership.server.mobile.validator.controller.MobileValidatorController;
import com.git.myth.gourd.membership.server.mobile.validator.persistance.redis.MobileValidatorRedisPersistance;
import com.git.myth.gourd.membership.server.mobile.validator.persistance.redis.MobileValidatorRedisPool;

@Configuration
public class ImportMobileValidatorSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {

		MultiValueMap<String, Object> attributes = importingClassMetadata
				.getAllAnnotationAttributes(EnableMobileValidator.class.getName(), false);
		
		List<String> beanClassList = new ArrayList<String>();
		String codeGeneratorClass = ((Class<?>)attributes.get("codeGeneratorClass").get(0)).getName();
		beanClassList.add(codeGeneratorClass);
		String persistanceClass = ((Class<?>)attributes.get("persistanceClass").get(0)).getName();
		if (MobileValidatorRedisPersistance.class.getName().equals(persistanceClass))
		{
			beanClassList.add(MobileValidatorRedisPool.class.getName());
		}
		beanClassList.add(persistanceClass);
		boolean enableApi = (Boolean)attributes.get("enableApi").get(0);
		if (enableApi)
		{
			beanClassList.add(MobileValidatorController.class.getName());
		}
		String[] strArray = new String[]{};
		return beanClassList.toArray(strArray);
	}
}