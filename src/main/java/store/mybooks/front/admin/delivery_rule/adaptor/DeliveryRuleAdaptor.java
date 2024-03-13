package store.mybooks.front.admin.delivery_rule.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleModifyRequest;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleRegisterRequest;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule.adaptor <br/>
 * fileName       : DeliveryRuleAdaptor<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/11/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/11/24        Fiat_lux       최초 생성<br/>
 */
@Component
@RequiredArgsConstructor
public class DeliveryRuleAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/delivery-rules";
    private static final String ADMIN = "/api/admin/delivery-rules";

    /**
     * Gets all delivery rule.
     *
     * @return the all delivery rule
     */
    public List<DeliveryRuleResponse> getAllDeliveryRule() {
        ResponseEntity<List<DeliveryRuleResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeliveryRuleResponse>>() {
                }
        );
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * Gets delivery rule.
     *
     * @param id the id
     * @return the delivery rule
     */
    public DeliveryRuleResponse getDeliveryRule(Integer id) {
        ResponseEntity<DeliveryRuleResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DeliveryRuleResponse>() {
                },
                id);

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * Create delivery rule delivery rule response.
     *
     * @param deliveryRuleRegisterRequest the delivery rule register request
     * @return the delivery rule response
     */
    @RequiredAuthorization
    public DeliveryRuleResponse createDeliveryRule(DeliveryRuleRegisterRequest deliveryRuleRegisterRequest) {
        ResponseEntity<DeliveryRuleResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + ADMIN,
                HttpMethod.POST,
                new HttpEntity<>(deliveryRuleRegisterRequest, Utils.getAuthHeader()),
                new ParameterizedTypeReference<DeliveryRuleResponse>() {
                });

        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    /**
     * Modify delivery rule.
     *
     * @param deliveryRuleModifyRequest the delivery rule modify request
     */
    @RequiredAuthorization
    public void modifyDeliveryRule(DeliveryRuleModifyRequest deliveryRuleModifyRequest) {
        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + ADMIN + "/modify",
                HttpMethod.PUT,
                new HttpEntity<>(deliveryRuleModifyRequest, Utils.getAuthHeader()),
                new ParameterizedTypeReference<Void>() {
                });
        Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * Delete delivery rule.
     *
     * @param id the id
     */
    @RequiredAuthorization
    public void deleteDeliveryRule(Integer id) {
        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + ADMIN + "/{deliveryRuleId}",
                HttpMethod.DELETE,
                new HttpEntity<>(null, Utils.getAuthHeader()),
                new ParameterizedTypeReference<Void>() {
                },
                id);

        Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
