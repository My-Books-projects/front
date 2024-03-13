package store.mybooks.front.admin.delivery_rule.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule.dto <br/>
 * fileName       : DeliveryRuleResponse<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/11/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/11/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
public class DeliveryRuleResponse {

    private Integer id;

    private String deliveryRuleNameId;

    private String companyName;

    private Integer cost;

    private Integer ruleCost;

    private LocalDate createdDate;

    private Integer isAvailable;
}
