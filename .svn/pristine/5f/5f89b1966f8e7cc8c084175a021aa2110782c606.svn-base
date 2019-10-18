package com.tianyi.helmet.server.util;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Configuration
@EnableSwagger2 //Loads the spring beans required by the framework
@ComponentScan(basePackages = {"com.tianyi.helmet.server.controller.device"})
public class MySwaggerConfig {
    private static boolean enable = true;

    @Bean
    public Docket createRestApi() {

        ParameterBuilder param = new ParameterBuilder();
        param.name("user-token").description("user-token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        ParameterBuilder helmetParam = new ParameterBuilder();
        helmetParam.name("imei").description("头盔唯一识别码").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        List<Parameter> aParameters = new ArrayList<>();

        aParameters.add(param.build());
        aParameters.add(helmetParam.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().enable(enable)
                .globalOperationParameters(aParameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("田一头盔后台服务 v1.0.0")
                .description("田一头盔后台服务")
                .termsOfServiceUrl("http://ip:port/swagger-ui.html")
                .build();
    }
}
