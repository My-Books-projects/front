package store.mybooks.front.admin.delivery_rule_name.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.delivery_rule_name.dto.DeliveryRuleNameRequest;
import store.mybooks.front.admin.delivery_rule_name.dto.DeliveryRuleNameResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule_name.adaptor <br/>
 * fileName       : DeliveryRuleNameAdaptor<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/10/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/10/24        Fiat_lux       최초 생성<br/>
 */
@Component
@RequiredArgsConstructor
public class DeliveryRuleNameAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/delivery-name-rules";

    public List<DeliveryRuleNameResponse> getAllDeliveryRuleName() {
        ResponseEntity<List<DeliveryRuleNameResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeliveryRuleNameResponse>>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    public DeliveryRuleNameResponse getDeliveryRuleName(String id) {
        ResponseEntity<DeliveryRuleNameResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DeliveryRuleNameResponse>() {
                },
                id);

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    @RequiredAuthorization
    public DeliveryRuleNameResponse createDeliveryRuleName(DeliveryRuleNameRequest deliveryRuleNameRequest) {
        ResponseEntity<DeliveryRuleNameResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.POST,
                new HttpEntity<>(deliveryRuleNameRequest, Utils.getAuthHeader()),
                new ParameterizedTypeReference<DeliveryRuleNameResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    @RequiredAuthorization
    public void deleteDeliveryRuleName(String id) {
        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.DELETE,
                new HttpEntity<>(Utils.getAuthHeader()),
                new ParameterizedTypeReference<Void>() {
                },
                id);

        Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
