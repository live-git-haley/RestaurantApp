package com.cognixia.jump.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * Access through postman: http://localhost:8080/v2/api-docs
 * 
 * Access through browser: http://localhost:8080/swagger-ui.html
 * 
 * */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// method for deciding with apis to document
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis( RequestHandlerSelectors.withClassAnnotation(RestController.class) )
				.paths( PathSelectors.any() )
				//.paths( PathSelectors.ant("/api/review/**") )
				//.paths( PathSelectors.regex("/api.*delete.*") )
				.build()
				.apiInfo( apiDetails() );
		
	}
	
	private ApiInfo apiDetails() {
		
		return new ApiInfo(
				"Reviews API", 
				"Open source API for obtain/updating info on restaurants and their reviews.", 
				"1.0", 
				"Free to use", // add terms of use info here or just url to link to terms of use
				new Contact("Mathias", "http://github.com/mathiast3", "mathias_email_address@something.com"), 
				"API License", 
				"http://github.com/mathias3", 
				Collections.emptyList()
				);
		
	}
}
