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
package com.minlia.cloud.framework.config.logging;


import com.minlia.cloud.framework.common.constants.Constants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * Aspect for logging execution of service and repository Spring components.
 */
@Aspect
@Profile("!"+Constants.Profiles.PRODUCTION)
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    @Pointcut("within(com.mycompany.myapp.repository..*) || within(com.mycompany.myapp.service..*) || within(com.mycompany.myapp.web.rest..*)")
    public void loggingPointcut() {
    }

    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if (env.acceptsProfiles(Constants.Profiles.DEVELOPMENT)) {
            log.error("Exception in {}.{}() with cause = {} and exception {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause(), e);
        } else {
            log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause());
        }
    }

    @Around("loggingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

            throw e;
        }
    }
}
