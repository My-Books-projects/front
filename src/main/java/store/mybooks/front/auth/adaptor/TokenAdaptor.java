package store.mybooks.front.auth.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.dto.request.LogoutRequest;
import store.mybooks.front.auth.dto.request.RefreshTokenRequest;
import store.mybooks.front.auth.dto.request.TokenCreateRequest;
import store.mybooks.front.auth.dto.response.RefreshTokenResponse;
import store.mybooks.front.auth.dto.response.TokenCreateResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.jwt<br>
 * fileName       : tokenAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/28/24        masiljangajji       최초 생성
 */
@Component
@RequiredArgsConstructor
public class TokenAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    public TokenCreateResponse createToken(TokenCreateRequest tokenCreateRequest) {

        ResponseEntity<TokenCreateResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/auth", HttpMethod.POST,
                        new HttpEntity<>(tokenCreateRequest, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

    public RefreshTokenResponse refreshAccessToken(RefreshTokenRequest refreshTokenRequest) {

        ResponseEntity<RefreshTokenResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/auth/refresh", HttpMethod.POST,
                        new HttpEntity<>(refreshTokenRequest, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

    public void deleteRefreshToken(LogoutRequest logoutRequest){

        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/auth/logout", HttpMethod.DELETE,
                        new HttpEntity<>(logoutRequest, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });


        if (responseEntity.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new RuntimeException();
        }
    }


}
