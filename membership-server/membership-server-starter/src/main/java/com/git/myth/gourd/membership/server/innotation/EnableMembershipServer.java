package com.git.myth.gourd.membership.server.innotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@ComponentScan(basePackages = {
		"com.git.myth.gourd.membership.server.configuration"
})
public @interface EnableMembershipServer {

}
