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
package com.minlia.cloud.framework.common.web;

import com.minlia.cloud.framework.common.persistence.model.IEntity;
import org.springframework.stereotype.Component;

@Component
public class UriMapper implements IUriMapper {

    public UriMapper() {
        super();
    }

    // API

    /**
     * - note: at this point, the URI is transformed into plural (added `s`) in a hardcoded way - this will change in the future
     */
    @Override
    public <T extends IEntity> String getUriBase(final Class<T> clazz) {
        return clazz.getSimpleName().toString().toLowerCase() + "s";
    }

}
