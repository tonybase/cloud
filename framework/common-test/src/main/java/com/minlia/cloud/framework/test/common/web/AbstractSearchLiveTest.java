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

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static com.minlia.cloud.framework.common.search.ClientOperation.CONTAINS;
import static com.minlia.cloud.framework.common.search.ClientOperation.EQ;
import static com.minlia.cloud.framework.common.search.ClientOperation.NEG_EQ;
import static com.minlia.cloud.framework.common.spring.util.Profiles.CLIENT;
import static com.minlia.cloud.framework.common.spring.util.Profiles.TEST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.minlia.cloud.framework.common.interfaces.IDto;
import com.minlia.cloud.framework.test.common.client.template.IRestTemplate;
import com.minlia.cloud.framework.test.common.util.IDUtil;
import com.minlia.cloud.framework.test.common.web.util.ClientConstraintsUtil;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import com.minlia.cloud.framework.client.IDtoOperations;
import com.minlia.cloud.framework.common.search.ClientOperation;
import com.minlia.cloud.framework.common.util.SearchField;
import com.minlia.cloud.framework.test.common.test.contract.ISearchTest;
import com.minlia.cloud.framework.test.common.util.SearchIntegrationTestUtil;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

@SuppressWarnings("unchecked")
@ActiveProfiles({ CLIENT, TEST })
public abstract class AbstractSearchLiveTest<T extends IDto> extends AbstractSearchReadOnlyLiveTest<T>implements ISearchTest {

    public AbstractSearchLiveTest() {
        super();
    }

    // tests

    @Override
    @Test
    public final void whenSearchByNameIsPerformed_thenNoExceptions() {
        final T existingResource = getApi().create(createNewEntity());

        // When
//        final Triple<String, ClientOperation, String> nameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), EQ, existingResource.getName());
//        getApi().searchAll(nameConstraint);
    }

    // by id

    @Override
    @Test
    public final void givenResourceWithIdExists_whenResourceIsSearchedById_thenNoExceptions() {
        final T existingResource = getApi().create(createNewEntity());
        getApi().searchAsResponse(ClientConstraintsUtil.createIdConstraint(EQ, existingResource.getId()), null);
    }

    @Override
    @Test
    public final void givenResourceWithIdExists_whenResourceIsSearchedById_thenSearchOperationIsSuccessful() {
        final T existingResource = getApi().create(createNewEntity());

        // When
        final Response searchResponse = getApi().searchAsResponse(ClientConstraintsUtil.createIdConstraint(EQ, existingResource.getId()), null);

        // Then
        assertThat(searchResponse.getStatusCode(), is(200));
    }

    @Override
    @Test
    public final void givenResourceWithIdExists_whenResourceIsSearchedById_thenResourceIsFound() {
        final T existingResource = getApi().create(createNewEntity());

        // When
        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createIdConstraint(EQ, existingResource.getId()));

        // Then
        assertThat(found, hasItem(existingResource));
    }

    // by name

    @Override
    @Test
    public final void givenResourceWithNameExists_whenResourceIsSearchedByName_thenNoExceptions() {
        final T existingResource = getApi().create(createNewEntity());
//        getApi().searchAsResponse(null, ClientConstraintsUtil.createNameConstraint(EQ, existingResource.getName()));
    }

    @Override
    @Test
    public final void givenResourceWithNameExists_whenResourceIsSearchedByName_thenOperationIsSuccessful() {
        final T existingResource = getApi().create(createNewEntity());

        // When
//        final Response searchResponse = getApi().searchAsResponse(null, ClientConstraintsUtil.createNameConstraint(EQ, existingResource.getName()));

        // Then
//        assertThat(searchResponse.getStatusCode(), is(200));
    }

    @Override
    @Test
    public final void givenResourceWithNameExists_whenResourceIsSearchedByName_thenResourceIsFound() {
        final T existingResource = getApi().create(createNewEntity());

        // When
//        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createNameConstraint(EQ, existingResource.getName()));

        // Then
//        assertThat(found, hasItem(existingResource));
    }

    @Override
    @Test
    public final void givenResourceWithNameExists_whenSearchByNegatedNameIsPerformed_thenResourcesAreCorrect() {
        final T existingResource1 = getApi().create(createNewEntity());
        final T existingResource2 = getApi().create(createNewEntity());

        // When
//        final ImmutableTriple<String, ClientOperation, String> nameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), EQ, existingResource1.getName());
//        final List<T> searchResults = getApi().searchAll(nameConstraint);

        // Then
//        assertThat(searchResults, hasItem(existingResource1));
//        assertThat(searchResults, not(hasItem(existingResource2)));
    }

    @Override
    @Test
    public final void givenResourceWithNameExists_whenResourceIsSearchedByNameLowerCase_thenResourceIsFound() {
        final T existingResource = getApi().create(createNewEntity());

        // When
//        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createNameConstraint(EQ, existingResource.getName().toLowerCase()));

        // Then
//        assertThat(found, hasItem(existingResource));
    }

    // by name - contains

    @Override
    @Test
    public final void givenResourceWithNameExists_whenResourceIsSearchedByContainsExactName_thenNoExceptions() {
        final T existingResource = getApi().create(createNewEntity());
//        getApi().searchAsResponse(null, ClientConstraintsUtil.createNameConstraint(CONTAINS, existingResource.getName()));
    }

    @Override
    @Test
    public final void givenResourceWithNameExists_whenSearchByContainsEntireNameIsPerformed_thenResourceIsFound() {
        final T existingEntity = getApi().create(createNewEntity());

        // When
//        final ImmutableTriple<String, ClientOperation, String> nameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), CONTAINS, existingEntity.getName());
//        final List<T> searchResults = getApi().searchAll(nameConstraint);

        // Then
//        assertThat(searchResults, hasItem(existingEntity));
    }

    @Override
    @Test
    public final void givenResourceWithNameExists_whenSearchByContainsPartOfNameIsPerformed_thenResourceIsFound() {
        final T existingEntity = getApi().create(createNewEntity());
//        final String name = existingEntity.getName();
//        final String partOfName = name.substring(2);

        // When
//        final ImmutableTriple<String, ClientOperation, String> nameContainsConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), CONTAINS, partOfName);
//        final List<T> searchResults = getApi().searchAll(nameContainsConstraint);

        // Then
//        assertThat(searchResults, hasItem(existingEntity));
    }

    // starts with, ends with

    @Override
    @Test
    public final void givenResourceExists_whenSearchByStartsWithEntireNameIsPerformed_thenResourceIsFound() {
        final T newEntity = createNewEntity();
//        SearchIntegrationTestUtil.givenResourceExists_whenSearchByStartsWithEntireKeyIsPerformed_thenResourceIsFound(getApi(), newEntity, SearchField.name, ClientOperation.STARTS_WITH, newEntity.getName());
    }

    @Override
    @Test
    public final void givenResourceExists_whenSearchByStartsWithPartOfNameIsPerformed_thenResourceIsFound() {
        final T newEntity = createNewEntity();
//        SearchIntegrationTestUtil.givenResourceExists_whenSearchByStartsWithPartOfKeyIsPerformed_thenResourceIsFound(getApi(), newEntity, SearchField.name, ClientOperation.STARTS_WITH, newEntity.getName());
    }

    @Override
    @Test
    public final void givenResourceExists_whenSearchByEndsWithEntireNameIsPerformed_thenResourceIsFound() {
        final T newEntity = createNewEntity();
//        SearchIntegrationTestUtil.givenResourceExists_whenSearchByEndsWithEntireKeyIsPerformed_thenResourceIsFound(getApi(), newEntity, SearchField.name, ClientOperation.ENDS_WITH, newEntity.getName());
    }

    @Override
    @Test
    public final void givenResourceExists_whenSearchByEndsWithPartOfNameIsPerformed_thenResourceIsFound() {
        final T newEntity = createNewEntity();
//        SearchIntegrationTestUtil.givenResourceExists_whenSearchByEndsWithPartOfNameIsPerformed_thenResourceIsFound(getApi(), newEntity, SearchField.name, ClientOperation.ENDS_WITH, newEntity.getName());
    }

    @Test
    public final void givenResourceExists_whenSearchByStartsWithPartOfLowerCaseNameIsPerformed_thenResourceIsFound() {
        final T newEntity = createNewEntity();
//        SearchIntegrationTestUtil.givenResourceExists_whenSearchByStartsWithPartOfLowerCaseNameIsPerformed_thenResourceIsFound(getApi(), newEntity, SearchField.name, ClientOperation.ENDS_WITH, newEntity.getName());
    }

    // by id and name

    @Override
    @Test
    public final void givenResourceWithNameAndIdExists_whenResourceIsSearchedByCorrectIdAndCorrectName_thenOperationIsSuccessful() {
        final T existingResource = getApi().create(createNewEntity());

        // When
//        final Response searchResponse = getApi().searchAsResponse(ClientConstraintsUtil.createIdConstraint(EQ, existingResource.getId()), ClientConstraintsUtil.createNameConstraint(EQ, existingResource.getName()));

        // Then
//        assertThat(searchResponse.getStatusCode(), is(200));
    }

    @Override
    @Test
    public final void givenResourceWithNameAndIdExists_whenResourceIsSearchedByCorrectIdAndCorrectName_thenResourceIsFound() {
        final T existingResource = getApi().create(createNewEntity());

        // When
//        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createIdConstraint(EQ, existingResource.getId()), ClientConstraintsUtil.createNameConstraint(EQ, existingResource.getName()));

        // Then
//        assertThat(found, hasItem(existingResource));
    }

    @Override
    @Test
    public final void givenResourceWithNameAndIdExists_whenResourceIsSearchedByCorrectIdAndIncorrectName_thenResourceIsNotFound() {
        final T existingResource = getApi().create(createNewEntity());

        // When
        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createIdConstraint(EQ, existingResource.getId()), ClientConstraintsUtil.createNameConstraint(EQ, randomAlphabetic(8)));

        // Then
        assertThat(found, not(hasItem(existingResource)));
    }

    @Override
    @Test
    public final void givenResourceWithNameAndIdExists_whenResourceIsSearchedByIncorrectIdAndCorrectName_thenResourceIsNotFound() {
        final T existingResource = getApi().create(createNewEntity());

        // When
//        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createIdConstraint(EQ, IDUtil.randomPositiveLong()), ClientConstraintsUtil.createNameConstraint(EQ, existingResource.getName()));

        // Then
//        assertThat(found, not(hasItem(existingResource)));
    }

    @Override
    @Test
    public final void givenResourceWithNameAndIdExists_whenResourceIsSearchedByIncorrectIdAndIncorrectName_thenResourceIsNotFound() {
        final T existingResource = getApi().create(createNewEntity());

        // When
        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createIdConstraint(EQ, IDUtil.randomPositiveLong()), ClientConstraintsUtil.createNameConstraint(EQ, randomAlphabetic(8)));

        // Then
        assertThat(found, not(hasItem(existingResource)));
    }

    // by negated id, name

    @Override
    @Test
    public final void givenResourceExists_whenResourceIsSearchedByNegatedName_thenOperationIsSuccessful() {
        final T existingResource = getApi().create(createNewEntity());

//        final Triple<String, ClientOperation, String> negatedNameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), NEG_EQ, existingResource.getName());

        // When
//        final Response searchResponse = getApi().searchAsResponse(null, negatedNameConstraint);

        // Then
//        assertThat(searchResponse.getStatusCode(), is(200));
    }

    @Override
    @Test
    public final void givenResourceExists_whenResourceIsSearchedByNegatedId_thenOperationIsSuccessful() {
        final T existingResource = getApi().create(createNewEntity());

        final Triple<String, ClientOperation, String> negatedIdConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.id.toString(), NEG_EQ, existingResource.getId().toString());

        // When
        final Response searchResponse = getApi().searchAsResponse(negatedIdConstraint, null);

        // Then
        assertThat(searchResponse.getStatusCode(), is(200));
    }

    @Override
    @Test
    public final void givenResourceExists_whenResourceIsSearchedByNegatedId_thenResourceIsNotFound() {
        final T existingResource = getApi().create(createNewEntity());

        // When
        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createIdConstraint(NEG_EQ, existingResource.getId()));

        // Then
        assertThat(found, not(hasItem(existingResource)));
    }

    @Override
    @Test
    public final void givenResourcesExists_whenResourceIsSearchedByNegatedId_thenTheOtherResourcesAreFound() {
        final T existingResource1 = getApi().create(createNewEntity());
        final T existingResource2 = getApi().create(createNewEntity());

        // When
        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createIdConstraint(NEG_EQ, existingResource1.getId()));

        // Then
        assertThat(found, hasItem(existingResource2));
    }

    @Override
    @Test
    public final void givenResourceAndOtherResourcesExists_whenResourceIsSearchedByNegatedName_thenResourcesAreFound() {
        final T existingResource1 = getApi().create(createNewEntity());
        final T existingResource2 = getApi().create(createNewEntity());

        // When
//        final ImmutableTriple<String, ClientOperation, String> nameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), NEG_EQ, existingResource1.getName());
//        final List<T> searchResults = getApi().searchAll(nameConstraint);

        // Then
//        assertThat(searchResults, not(hasItem(existingResource1)));
//        assertThat(searchResults, hasItem(existingResource2));
    }

    @Override
    @Test
    public final void givenResourceAndOtherResourcesExists_whenResourceIsSearchedByNegatedId_thenResourcesAreFound() {
        final T existingResource1 = getApi().create(createNewEntity());
        final T existingResource2 = getApi().create(createNewEntity());

        // When
        final ImmutableTriple<String, ClientOperation, String> idConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.id.toString(), NEG_EQ, existingResource1.getId().toString());
        final List<T> searchResults = getApi().searchAll(idConstraint);

        // Then
        assertThat(searchResults, not(hasItem(existingResource1)));
        assertThat(searchResults, hasItem(existingResource2));
    }

    // with paging

    @Test
    public final void givenResourceExists_whenResourceIsSearchedByNameWithPaging_then200IsReceived() {
        final T existingResource = getApi().create(createNewEntity());

        // When
//        final Triple<String, ClientOperation, String> nameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), EQ, existingResource.getName());
//        final Response searchResponse = getApi().searchAsResponse(null, nameConstraint, 0, 2);

        // Then
//        assertThat(searchResponse.getStatusCode(), is(200));
    }

    @Test
    public final void givenResourcesExists_whenResourceIsSearchedByNameWithPagingOfSize2_thenMax2ResourcesAreReceived() {
        final T existingResource1 = getApi().create(createNewEntity());
        getApi().create(createNewEntity());
        getApi().create(createNewEntity());
        getApi().create(createNewEntity());

        // When
//        final Triple<String, ClientOperation, String> nameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), NEG_EQ, existingResource1.getName());
//        final List<T> searchResults = getApi().searchPaginated(null, nameConstraint, 0, 2);

        // Then
//        assertThat(searchResults.size(), is(2));
    }

    // template

    protected T createNewEntity() {
        return getEntityOps().createNewResource();
    }

    @Override
    protected abstract IRestTemplate<T> getApi();

    protected abstract IDtoOperations<T> getEntityOps();

    protected final RequestSpecification givenReadAuthenticated() {
        return getApi().givenReadAuthenticated();
    }

}
