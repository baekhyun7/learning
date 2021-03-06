package com.zh.learning.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
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

import java.util.List;

/**
 * @author zh
 * @version 1.0
 * @date 2020/10/30 11:02
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
//        ParameterBuilder parameterBuilder=new ParameterBuilder();
//        parameterBuilder.name("zh_token").description("token令牌").modelRef(new ModelRef("String"))
//                .parameterType("header")
//                .required(true).build();
        List<Parameter> parameters= Lists.newArrayList();
//        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("zh API Doc")
                .description("This is a restful api document of zh.")
                .version("1.0")
                .build();
    }
}
