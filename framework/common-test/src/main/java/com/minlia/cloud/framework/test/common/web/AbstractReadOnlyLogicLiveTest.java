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
package com.minlia.cloud.framework.test.common.web;

import static com.jayway.restassured.RestAssured.get;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static com.minlia.cloud.framework.common.spring.util.Profiles.CLIENT;
import static com.minlia.cloud.framework.common.spring.util.Profiles.TEST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.minlia.cloud.framework.common.interfaces.IDto;
import com.minlia.cloud.framework.test.common.client.template.IRestTemplate;
import com.minlia.cloud.framework.test.common.util.IDUtil;
import com.minlia.cloud.framework.common.util.SearchField;
import com.minlia.cloud.framework.common.web.WebConstants;
import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import com.google.common.base.Preconditions;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

@ActiveProfiles({ CLIENT, TEST })
public abstract class AbstractReadOnlyLogicLiveTest<T extends IDto> {

    protected final Class<T> clazz;

    public AbstractReadOnlyLogicLiveTest(final Class<T> clazzToSet) {
        super();

        Preconditions.checkNotNull(clazzToSet);
        clazz = clazzToSet;
    }

    // tests

    // find - one

    @Test
    /*code*/public void givenResourceForIdDoesNotExist_whenResourceOfThatIdIsRetrieved_then404IsReceived() {
        final Response response = getApi().findOneAsResponse(IDUtil.randomPositiveLong(), null);

        assertThat(response.getStatusCode(), is(404));
    }

    @Test
    /*code*/public void whenResourceIsRetrievedByNonNumericId_then400IsReceived() {
        // Given id is non numeric
        final String id = randomAlphabetic(6);

        // When
        final Response res = getApi().findOneByUriAsResponse(getUri() + WebConstants.PATH_SEP + id, null);

        // Then
        assertThat(res.getStatusCode(), is(400));
    }

    // find - all

    @Test
    /*code*/public final void whenAllResourcesAreRetrieved_then200IsReceived() {
        // When
        final Response response = getApi().findAllAsResponse(null);

        // Then
        assertThat(response.getStatusCode(), is(200));
    }

    // find - all - pagination

    @Test
    /*code*/public final void whenResourcesAreRetrievedPaginated_then200IsReceived() {
        // When
        final Response response = getApi().findAllPaginatedAsResponse(0, 1, null);

        // Then
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    // - note: may fail intermittently - TODO: investigate
    /*code*/public final void whenPageOfResourcesIsRetrievedOutOfBounds_then404IsReceived() {
        // When
        final Response response = getApi().findAllPaginatedAsResponse(Integer.parseInt(randomNumeric(5)), 1, null);

        // Then
        assertThat(response.getStatusCode(), is(404));
    }

    @Test
    /*code*/public final void whenResourcesAreRetrievedWithNonNumericPage_then400IsReceived() {
        // When
        final Response response = get(getUri() + "?page=" + randomAlphabetic(5).toLowerCase() + "&size=1");

        // Then
        assertThat(response.getStatusCode(), is(400));
    }

    @Test
    /*code*/public final void whenResourcesAreRetrievedWithNonNumericPageSize_then400IsReceived() {
        // When
        final Response response = get(getUri() + "?page=0" + "&size=" + randomAlphabetic(5));

        // Then
        assertThat(response.getStatusCode(), is(400));
    }

    // find - all - sorting

    @Test
    /*code*/public final void whenResourcesAreRetrievedSorted_then200IsReceived() {
        final Response response = getApi().findAllSortedAsResponse(SearchField.name.name(), Sort.Direction.ASC.name(), null);

        assertThat(response.getStatusCode(), is(200));
    }

    // find - all - pagination and sorting

    @Test
    /*code*/public final void whenResourcesAreRetrievedPaginatedAndSorted_then200IsReceived() {
        final Response response = getApi().findAllPaginatedAndSortedAsResponse(0, 1, SearchField.name.name(), Sort.Direction.ASC.name(), null);

        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    /*code*/public final void whenResourcesAreRetrievedByPaginatedAndWithInvalidSorting_then400IsReceived() {
        // When
        final Response response = getApi().findAllPaginatedAndSortedAsResponse(0, 4, "invalid", null, null);

        // Then
        assertThat(response.getStatusCode(), is(400));
    }

    // count

    @Test
    /*code*/public final void whenCountIsPerformed_then200IsReceived() {
        // When
        final Response response = getApi().countAsResponse();

        // Then
        assertThat(response.getStatusCode(), is(200));
    }

    // template method

    protected abstract IRestTemplate<T> getApi();

    protected final String getUri() {
        return getApi().getUri() + WebConstants.PATH_SEP;
    }

    protected final RequestSpecification givenReadAuthenticated() {
        return getApi().givenReadAuthenticated();
    }

}
