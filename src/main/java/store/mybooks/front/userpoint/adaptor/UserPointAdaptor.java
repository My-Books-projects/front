package store.mybooks.front.userpoint.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.userpoint.dto.response.PointResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.userpoint.adaptor<br>
 * fileName       : UserPointAdaptor<br>
 * author         : minsu11<br>
 * date           : 3/10/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/10/24        minsu11       최초 생성<br>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserPointAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/point-histories";
    private static final String ADMIN = "/api/admin/point-histories";
    private static final String MEMBER = "/api/member/point-histories";

    @RequiredAuthorization
    public PointResponse getPointsHeld() {
        ResponseEntity<PointResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + MEMBER + "/points",
                HttpMethod.GET,
                new HttpEntity<>(Utils.getAuthHeader()),
                new ParameterizedTypeReference<>() {
                }
        );
        log.info("log 끝남 {}", exchange.getBody().getRemainingPoint());
        return Utils.getResponseEntity(exchange, HttpStatus.OK);

    }
}
