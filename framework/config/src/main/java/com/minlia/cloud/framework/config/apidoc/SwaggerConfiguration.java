/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/framework/2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//package com.mycompany.myapp.config.apidoc;
//
//import com.minlia.cloud.framework.config.MinliaProperties;
//import com.mycompany.myapp.config.Constants;
//import com.mycompany.myapp.config.JHipsterProperties;
//
//import java.util.Date;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StopWatch;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
///**
// * Springfox Swagger configuration.
// *
// * Warning! When having a lot of REST endpoints, Springfox can become a performance issue. In that
// * case, you can use a specific Spring profile for this class, so that only front-end developers
// * have access to the Swagger view.
// */
//@Configuration
//@EnableSwagger2
//@Profile("!"+Constants.SPRING_PROFILE_PRODUCTION)
//public class SwaggerConfiguration {
//
//    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);
//
//    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
//
//    /**
//     * Swagger Springfox configuration.
//     */
//    @Bean
//    public Docket swaggerSpringfoxDocket(MinliaProperties jHipsterProperties) {
//        log.debug("Starting Swagger");
//        StopWatch watch = new StopWatch();
//        watch.start();
//        ApiInfo apiInfo = new ApiInfo(
//            jHipsterProperties.getSwagger().getTitle(),
//            jHipsterProperties.getSwagger().getDescription(),
//            jHipsterProperties.getSwagger().getVersion(),
//            jHipsterProperties.getSwagger().getTermsOfServiceUrl(),
//            jHipsterProperties.getSwagger().getContact(),
//            jHipsterProperties.getSwagger().getLicense(),
//            jHipsterProperties.getSwagger().getLicenseUrl());
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo)
//            .genericModelSubstitutes(ResponseEntity.class)
//            .forCodeGeneration(true)
//            .genericModelSubstitutes(ResponseEntity.class)
//            .directModelSubstitute(java.time.LocalDate.class, String.class)
//            .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
//            .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
//            .select()
//            .paths(regex(DEFAULT_INCLUDE_PATTERN))
//            .build();
//        watch.stop();
//        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
//        return docket;
//    }
//}
