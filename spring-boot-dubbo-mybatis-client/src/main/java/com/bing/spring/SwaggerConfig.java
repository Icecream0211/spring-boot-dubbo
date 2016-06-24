package com.bing.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by patterncat on 2016-05-05.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().paths(paths()).build().apiInfo(apiInfo());
    }

    @SuppressWarnings("unchecked")
    private Predicate<String> paths() {
        return Predicates.or(Predicates.containsPattern("/*"));
    }

    private ApiInfo apiInfo() {
        String title = "my api";
        String description = "api desc";
        String version = "1.0.0-snapshot";
        String contact = "patterncat@gmail.com";
        ApiInfo apiInfo = new ApiInfo(title,description,version,
                "terms of service", contact, "", "");
        return apiInfo;
    }
}