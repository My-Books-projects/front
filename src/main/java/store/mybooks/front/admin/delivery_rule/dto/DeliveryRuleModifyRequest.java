package store.mybooks.front.admin.delivery_rule.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule.dto <br/>
 * fileName       : DeliveryRuleModifyRequest<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/11/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/11/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@AllArgsConstructor
public class DeliveryRuleModifyRequest {

    private Integer id;

    @NotBlank
    @Size(min = 1, max = 20)
    private String deliveryRuleCompanyName;

    @NotNull
    @Min(0)
    private Integer deliveryCost;

    @NotNull
    @Min(0)
    private Integer deliveryRuleCost;
}
