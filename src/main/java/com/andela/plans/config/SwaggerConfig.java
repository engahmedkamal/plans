package com.andela.plans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final static String TITLE = "Members Plans REST API";
    private final static String DESCRIPTION = "Members Plans REST API";
    private final static String VERSION = "1.0";
    private final static String TERMS_OF_SERVICE = "Terms of service";
    private final static String CONTACT_PERSON_NAME = "Andela";
    private final static String CONTACT_PERSON_URL = "";
    private final static String CONTACT_PERSON_EMAIL = "ahmed.kamal@andela.com";
    private final static String LICENSE = "All rights for Andela";
    private final static String LICENSE_URL = "";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("DateTime")
                .directModelSubstitute(LocalTime.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.andela.plans.controller"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(TITLE, DESCRIPTION, VERSION, TERMS_OF_SERVICE,
                new Contact(CONTACT_PERSON_NAME, CONTACT_PERSON_URL, CONTACT_PERSON_EMAIL),
                LICENSE, LICENSE_URL);
        return apiInfo;
    }
}
