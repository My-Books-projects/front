package store.mybooks.front.admin.category.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.category.model.request.CategoryCreateRequestForTransmission;
import store.mybooks.front.admin.category.model.request.CategoryModifyRequestForTransmission;
import store.mybooks.front.admin.category.model.response.CategoryGetResponse;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForBookCreate;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForUpdate;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForView;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.category.adaptor
 * fileName       : CategoryAdaptorImpl
 * author         : damho-lee
 * date           : 2/24/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/24/24          damho-lee          최초 생성
 */
@Component
@RequiredArgsConstructor
public class CategoryAdminAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/categories";

    private static final String URL_ADMIN = "/api/admin/categories";

    /**
     * methodName : getHighestCategories
     * author : newjaehun
     * description : 1단계 카테고리 리스트 조회.
     *
     * @return list
     */
    public List<CategoryGetResponse> getHighestCategories() {
        HttpHeaders headers = Utils.getAuthHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<CategoryGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/highest",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    /**
     * methodName : getChildCategories
     * author : newjaehun
     * description : 2단계 카테고리 리스트 조회.
     *
     * @param parentCategoryId Long
     * @return list
     */
    public List<CategoryGetResponse> getChildCategories(long parentCategoryId) {
        HttpHeaders headers = Utils.getAuthHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<CategoryGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/parentCategoryId/" + parentCategoryId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    /**
     * methodName : createCategory
     * author : newjaehun
     * description : 카테고리 생성 요청.
     *
     * @param categoryCreateRequest CategoryCreateRequestForTransmission
     */
    @RequiredAuthorization
    public void createCategory(CategoryCreateRequestForTransmission categoryCreateRequest) {
        HttpHeaders headers = Utils.getAuthHeader();

        HttpEntity<CategoryCreateRequestForTransmission> requestEntity =
                new HttpEntity<>(categoryCreateRequest, headers);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ADMIN,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }

    /**
     * methodName : getCategories
     * author : newjaehun
     * description : 카테고리 뷰를 위한 페이징된 카테고리들 요청.
     *
     * @param pageable Pageable
     * @return page response
     */
    @RequiredAuthorization
    public PageResponse<CategoryGetResponseForView> getCategories(Pageable pageable) {
        HttpHeaders headers = Utils.getAuthHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PageResponse<CategoryGetResponseForView>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ADMIN
                        + "/page?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    /**
     * methodName : getCategories
     * author : newjaehun
     * description : 도서 생성에 사용할 카테고리 리스트 요청.
     *
     * @return list
     */
    @RequiredAuthorization
    public List<CategoryGetResponseForBookCreate> getCategories() {
        HttpHeaders headers = Utils.getAuthHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String url = gatewayAdaptorProperties.getAddress() + URL_ADMIN;
        ResponseEntity<List<CategoryGetResponseForBookCreate>> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : updateCategory
     * author : newjaehun
     * description : 카테고리 업데이트 요청.
     *
     * @param id Integer
     * @param categoryModifyRequestForTransmission CategoryModifyRequestForTransmission
     */
    @RequiredAuthorization
    public void updateCategory(Integer id, CategoryModifyRequestForTransmission categoryModifyRequestForTransmission) {
        HttpHeaders headers = Utils.getAuthHeader();

        HttpEntity<CategoryModifyRequestForTransmission> requestEntity =
                new HttpEntity<>(categoryModifyRequestForTransmission, headers);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ADMIN + "/" + id,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    /**
     * methodName : deleteCategory
     * author : newjaehun
     * description : 카테고리 삭제 요창.
     *
     * @param id Integer
     */
    @RequiredAuthorization
    public void deleteCategory(Integer id) {
        HttpHeaders headers = Utils.getAuthHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ADMIN + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    /**
     * methodName : getCategory
     * author : newjaehun
     * description : 카테고리 업데이트에 사용할 카테고리 요청.
     *
     * @param id Integer
     * @return category get response for update
     */
    @RequiredAuthorization
    public CategoryGetResponseForUpdate getCategory(Integer id) {
        HttpHeaders headers = Utils.getAuthHeader();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CategoryGetResponseForUpdate> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ADMIN + "/categoryId/" + id,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }
}
