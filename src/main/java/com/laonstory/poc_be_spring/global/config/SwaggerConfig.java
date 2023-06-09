package com.laonstory.poc_be_spring.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;


@Configuration
public class SwaggerConfig {


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("PoC")
                .version("Austin")
                .description("PoC \n contextPath = 192.168.1.48:10024/api/poc \n \n " +
                        "table \n \n" +
                        "company - 거래처,제조사,공장 \n" +
                        "document - 문서 \n" +
                        "examination - 시험신청 \n" +
                        "examinationHistory - 시험신청 기록 \n" +
                        "pic - 담당자 \n" +
                        "proReferenceFile - 참고자료 \n" +
                        "requirement - 요구사항 \n" +
                        "test - 시험정 \n" +
                        "stdxxxx - 규격정보 \n" +
                        "user - 회원")
                .build();
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentType() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        produces.add("multipart/form-data");
        return produces;
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(bearerAuth()).build();
    }

    private List<SecurityReference> bearerAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentType())
                .groupName("APP Server")
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(Predicate.not(RequestHandlerSelectors.basePackage("com.laonstory.poc.domain.manager")))
                .paths(PathSelectors.ant("/api/poc/**"))
                .build();
    }

    @Bean
    public Docket managerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentType())
                .groupName("Manager")
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/brc/manage/**"))
                .build();
    }


}
