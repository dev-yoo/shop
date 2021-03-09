package com.shop.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* <pre>
* com.lotte.ndp.diquest.config
* |_ SwaggerConfig.java
*
* 개요 :
* -----------------------------------------------------------------------
* 변경일                   작성자                           변경내용
* ----------- ------------------- ---------------------------------------
* 2020. 07. 16.   NHN다이퀘스트 유대성         최초 작성
* ----------------------------------------------------------------------- 
* <pre>
* @version : 1.0
* @author : 유대성
* @see : 
*/
@Configuration
@EnableSwagger2 
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig implements WebMvcConfigurer {
 
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(getConsumeContentTypes())
	            .produces(getProduceContentTypes())
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.shop.controller"))
				.paths(PathSelectors.any()).build().genericModelSubstitutes(Optional.class, Mono.class, Flux.class)
				.securityContexts(Arrays.asList(securityContext())) 
				.securitySchemes(Arrays.asList(apiKey()));
	}
	
	private ApiKey apiKey() {
		  return new ApiKey("API Auth Key", "api-token", "header");
	}

	@SuppressWarnings("deprecation")
	private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReference())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> securityReference() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("API Auth Key", authorizationScopes));
    }
		
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
						.title("Shop")
						.version("0.0.1-SNAPSHOT")
						.build();
	}
	
    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json");
        return produces;
    }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/swagger/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
		registry.addRedirectViewController("/swagger/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/swagger/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
		registry.addRedirectViewController("/swagger/swagger-resources", "/swagger-resources");
		registry.addRedirectViewController("/swagger/", "/swagger/swagger-ui.html");
		registry.addRedirectViewController("/swagger/csrf", "/swagger/swagger-ui.html");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
}