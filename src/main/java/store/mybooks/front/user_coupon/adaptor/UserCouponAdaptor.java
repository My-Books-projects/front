package store.mybooks.front.user_coupon.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.user_coupon.model.response.UserCouponGetResponse;
import store.mybooks.front.user_coupon.model.response.UserCouponGetResponseForOrder;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.user_coupon.adaptor
 * fileName       : UserCouponAdaptor
 * author         : damho-lee
 * date           : 3/5/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/5/24          damho-lee          최초 생성
 */
@Component
@RequiredArgsConstructor
public class UserCouponAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL_USER = "/api/member/user-coupon";

    /**
     * methodName : getUserCoupons <br>
     * author : damho-lee <br>
     * description : 마이페이지 회원 쿠폰함에서 사용할 조회 메서드.<br>
     *
     * @param pageable Pageable
     * @return page response
     */
    @RequiredAuthorization
    public PageResponse<UserCouponGetResponse> getUserCoupons(Pageable pageable) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(Utils.getAuthHeader());

        ResponseEntity<PageResponse<UserCouponGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_USER + "/page?page=" + pageable.getPageNumber()
                        + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : getUsableUserCoupon <br>
     * author : damho-lee <br>
     * description : 도서에 적용할 수 있는 회원 쿠폰 리스트 조회.<br>
     *
     * @param bookId Long
     * @return list
     */
    @RequiredAuthorization
    public List<UserCouponGetResponseForOrder> getUsableUserCoupon(Long bookId) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(Utils.getAuthHeader());

        ResponseEntity<List<UserCouponGetResponseForOrder>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_USER + "/usable-coupon/" + bookId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : getUsableUserTotalCoupon <br>
     * author : damho-lee <br>
     * description : 주문 전체에 적용할 수 있는 회원 쿠폰 리스트 조회.<br>
     *
     * @return list
     */
    @RequiredAuthorization
    public List<UserCouponGetResponseForOrder> getUsableUserTotalCoupon() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(Utils.getAuthHeader());

        ResponseEntity<List<UserCouponGetResponseForOrder>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_USER + "/usable-coupon",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
