package store.mybooks.front.admin.delivery_rule_name.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.delivery_rule_name.adaptor.DeliveryRuleNameAdaptor;
import store.mybooks.front.admin.delivery_rule_name.dto.DeliveryRuleNameRequest;
import store.mybooks.front.admin.delivery_rule_name.dto.DeliveryRuleNameResponse;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule_name.service <br/>
 * fileName       : DeliveryRuleNameService<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/10/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/10/24        Fiat_lux       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class DeliveryRuleNameService {
    private final DeliveryRuleNameAdaptor deliveryRuleNameAdaptor;

    public List<DeliveryRuleNameResponse> getAllDeliveryRuleNames() {
        return deliveryRuleNameAdaptor.getAllDeliveryRuleName();
    }

    public DeliveryRuleNameResponse getDeliveryRule(String id) {
        return deliveryRuleNameAdaptor.getDeliveryRuleName(id);
    }

    public boolean createDeliveryRuleName(DeliveryRuleNameRequest deliveryRuleNameRequest) {
        try {
            deliveryRuleNameAdaptor.createDeliveryRuleName(deliveryRuleNameRequest);
            return true;
        } catch (RuntimeException e) {
            return false;
        }

    }

    public boolean deleteDeliveryRuleName(String id) {
        try {
            deliveryRuleNameAdaptor.deleteDeliveryRuleName(id);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
