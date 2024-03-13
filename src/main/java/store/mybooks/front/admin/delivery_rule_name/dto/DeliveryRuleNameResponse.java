package store.mybooks.front.admin.delivery_rule_name.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule_name.dto <br/>
 * fileName       : DeliveryRuleNameResponse<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/10/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/10/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
public class DeliveryRuleNameResponse {
    private String id;
    private LocalDate createdDate;
}
