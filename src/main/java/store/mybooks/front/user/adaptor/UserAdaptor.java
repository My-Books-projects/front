package store.mybooks.front.user.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.user.dto.request.UserCreateRequest;
import store.mybooks.front.user.dto.request.UserEmailRequest;
import store.mybooks.front.user.dto.request.UserGradeModifyRequest;
import store.mybooks.front.user.dto.request.UserModifyRequest;
import store.mybooks.front.user.dto.request.UserOauthCreateRequest;
import store.mybooks.front.user.dto.request.UserOauthLoginRequest;
import store.mybooks.front.user.dto.request.UserPasswordModifyRequest;
import store.mybooks.front.user.dto.request.UserStatusModifyRequest;
import store.mybooks.front.user.dto.response.UserCreateResponse;
import store.mybooks.front.user.dto.response.UserEncryptedPasswordResponse;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.user.dto.response.UserGradeModifyResponse;
import store.mybooks.front.user.dto.response.UserInactiveVerificationResponse;
import store.mybooks.front.user.dto.response.UserLoginResponse;
import store.mybooks.front.user.dto.response.UserModifyResponse;
import store.mybooks.front.user.dto.response.UserOauthCreateResponse;
import store.mybooks.front.user.dto.response.UserPasswordModifyResponse;
import store.mybooks.front.user.dto.response.UserStatusModifyResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.user.adaptor<br>
 * fileName       : UserAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 2/23/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/23/24        masiljangajji       최초 생성
 */
@Component
@RequiredArgsConstructor
public class UserAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/users";
    private static final String URL_MEMBER = "/api/member/users";
    private static final String URL_ADMIN_ID = "/api/admin/users/{userId}";
    private static final String URL_ADMIN = "/api/admin/users/"; // todo 전체유저 조회


    public UserEncryptedPasswordResponse verifyUserStatus(UserEmailRequest request) {

        ResponseEntity<UserEncryptedPasswordResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL + "/verification", HttpMethod.POST,
                        new HttpEntity<>(request, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

    @RequiredAuthorization
    public void verifyDormancyUser() {

        ResponseEntity<UserInactiveVerificationResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER + "/verification/dormancy",
                        HttpMethod.POST,
                        new HttpEntity<>(Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    @RequiredAuthorization
    public void verifyLockUser(UserPasswordModifyRequest request) {

        ResponseEntity<UserInactiveVerificationResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER + "/verification/lock",
                        HttpMethod.POST,
                        new HttpEntity<>(request, Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    public UserLoginResponse completeLoginProcess(UserEmailRequest request) {
        ResponseEntity<UserLoginResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL + "/verification/complete",
                        HttpMethod.POST,
                        new HttpEntity<>(request, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

    public UserLoginResponse loginOauthUser(UserOauthLoginRequest userLoginRequest) {

        ResponseEntity<UserLoginResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL + "/oauth/login", HttpMethod.POST,
                        new HttpEntity<>(userLoginRequest, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }


    /**
     * methodName : createUser
     * author : masiljangajji
     * description : 유저의 회원가입 요청을 처리함
     *
     * @param createRequest request
     */
    public void createUser(UserCreateRequest createRequest) {

        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<UserCreateRequest> requestEntity = new HttpEntity<>(createRequest, headers);

        ResponseEntity<UserCreateResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL, HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });


        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }

    public UserOauthCreateResponse createOauthUser(UserOauthCreateRequest createRequest) {

        ResponseEntity<UserOauthCreateResponse> response =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL + "/oauth", HttpMethod.POST,
                        new HttpEntity<>(createRequest, Utils.getHttpHeader())
                        , new ParameterizedTypeReference<>() {
                        });

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
        return response.getBody();
    }


    /**
     * methodName : findUserById
     * author : masiljangajji
     * description : 유저의 Id로 유저의 정보를 가져옴
     *
     * @return user get response
     */
    @RequiredAuthorization
    public UserGetResponse findUser() {

        ResponseEntity<UserGetResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return responseEntity.getBody();
    }


    /**
     * methodName : modifyUserPassword
     * author : masiljangajji
     * description : 유저의 비밀번호를 변경함
     *
     * @param modifyRequest request
     */
    @RequiredAuthorization
    public void modifyUserPassword(UserPasswordModifyRequest modifyRequest) {

        ResponseEntity<UserPasswordModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER + "/password",
                        HttpMethod.PUT,
                        new HttpEntity<>(modifyRequest, Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * methodName : modifyUserStatus
     * author : masiljangajji
     * description : 유저의 상태를 변경함
     *
     * @param userId        id
     * @param modifyRequest request
     */

    @RequiredAuthorization
    public void modifyUserStatus(Long userId, UserStatusModifyRequest modifyRequest) {

        HttpHeaders headers = Utils.getHttpHeader();

        HttpEntity<UserStatusModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserStatusModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_ADMIN_ID + "/status",
                        HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * methodName : modifyUserGrade
     * author : masiljangajji
     * description : 유저의 등급을 변경함
     *
     * @param userId        id
     * @param modifyRequest request
     */
    @RequiredAuthorization
    public void modifyUserGrade(Long userId, UserGradeModifyRequest modifyRequest) {

        HttpHeaders headers = Utils.getHttpHeader();

        HttpEntity<UserGradeModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserGradeModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_ADMIN_ID + "/grade",
                        HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * methodName : modifyUser
     * author : masiljangajji
     * description : 유저의 정보를 변경함 (이름,전화번호)
     *
     * @param modifyRequest request
     */
    @RequiredAuthorization
    public void modifyUser(UserModifyRequest modifyRequest) {


        ResponseEntity<UserModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER, HttpMethod.PUT,
                        new HttpEntity<>(modifyRequest, Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * methodName : deleteUser
     * author : masiljangajji
     * description : 회원탈퇴
     */
    @RequiredAuthorization
    public void deleteUser() {

        ResponseEntity<UserModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER, HttpMethod.DELETE,
                        new HttpEntity<>(Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

}