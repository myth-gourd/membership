package com.git.myth.gourd.membership.server.mobile.innotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.git.myth.gourd.membership.server.mobile.validator.MobileValidatorCodeGenerator;
import com.git.myth.gourd.membership.server.mobile.validator.impl.MobileValidatorCodeGeneratorImpl;
import com.git.myth.gourd.membership.server.mobile.validator.persistance.MobileValidatorPersistance;
import com.git.myth.gourd.membership.server.mobile.validator.persistance.redis.MobileValidatorRedisPersistance;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
public @interface MembershipMobile {

	boolean enableApi() default true;
	
	Class<? extends MobileValidatorPersistance> persistanceClass() default MobileValidatorRedisPersistance.class;

	Class<? extends MobileValidatorCodeGenerator> codeGeneratorClass() default MobileValidatorCodeGeneratorImpl.class;
}