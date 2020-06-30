package com.git.myth.gourd.membership.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Predicate;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	private static final String GROUP_NAME = "Membership Server";

	private static final String BASE_PACKAGE = "com.git.myth.gourd.membership.server";

	@Autowired
	private MembershipConfiguration configuration;

	@Bean
	public Docket docket() {
		Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
			@Override
			public boolean apply(RequestHandler input) {
				return true;
			}
		};
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(configuration.isEnableSwaggerUI())
				.apiInfo(getApiInfo())
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(true)
				.forCodeGeneration(true)
				.groupName(GROUP_NAME).select().apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
				.apis(predicate).build();
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("GROUP_NAME").contact(new Contact(GROUP_NAME, "", "")).version("lasted").build();
	}
}
