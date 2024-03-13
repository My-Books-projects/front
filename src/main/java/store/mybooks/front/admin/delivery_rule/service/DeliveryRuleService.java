package store.mybooks.front.admin.delivery_rule.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.delivery_rule.adaptor.DeliveryRuleAdaptor;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleModifyRequest;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleRegisterRequest;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleResponse;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule.service <br/>
 * fileName       : DeliveryRuleService<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/11/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/11/24        Fiat_lux       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class DeliveryRuleService {
    private final DeliveryRuleAdaptor deliveryRuleAdaptor;

    /**
     * Gets all delivery rule.
     *
     * @return the all delivery rule
     */
    public List<DeliveryRuleResponse> getAllDeliveryRule() {
        return deliveryRuleAdaptor.getAllDeliveryRule();
    }

    /**
     * Gets delivery rule.
     *
     * @param id the id
     * @return the delivery rule
     */
    public DeliveryRuleResponse getDeliveryRule(Integer id) {
        return deliveryRuleAdaptor.getDeliveryRule(id);
    }

    /**
     * Create delivery rule boolean.
     *
     * @param deliveryRuleRegisterRequest the delivery rule register request
     * @return the boolean
     */
    public void createDeliveryRule(DeliveryRuleRegisterRequest deliveryRuleRegisterRequest) {
        deliveryRuleAdaptor.createDeliveryRule(deliveryRuleRegisterRequest);
    }

    /**
     * Modify delivery rule boolean.
     *
     * @param deliveryRuleModifyRequest the delivery rule modify request
     * @return the boolean
     */
    public void modifyDeliveryRule(DeliveryRuleModifyRequest deliveryRuleModifyRequest) {
        deliveryRuleAdaptor.modifyDeliveryRule(deliveryRuleModifyRequest);
    }

    /**
     * Delete delivery rule.
     *
     * @param id the id
     */
    public void deleteDeliveryRule(Integer id) {
        deliveryRuleAdaptor.deleteDeliveryRule(id);
    }
}
