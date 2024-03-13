package store.mybooks.front.admin.delivery_rule_name.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.admin.delivery_rule_name.dto.DeliveryRuleNameRequest;
import store.mybooks.front.admin.delivery_rule_name.dto.DeliveryRuleNameResponse;
import store.mybooks.front.admin.delivery_rule_name.service.DeliveryRuleNameService;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule_name.controller <br/>
 * fileName       : DeliveryRuleNameController<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/10/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/10/24        Fiat_lux       최초 생성<br/>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/delivery-rule-name")
public class DeliveryRuleNameController {

    private final DeliveryRuleNameService deliveryRuleNameService;

    @GetMapping
    public String viewDeliveryRuleName(Model model) {
        List<DeliveryRuleNameResponse> allDeliveryRuleNames = deliveryRuleNameService.getAllDeliveryRuleNames();
        model.addAttribute("deliveryList", allDeliveryRuleNames);

        return "admin/view/delivery-rule-name/delivery-rule-name-view";
    }

    @GetMapping("/register")
    public String viewDeliveryRuleNameRegister() {
        return "admin/view/delivery-rule-name/delivery-rule-name-register-view";
    }

    @PostMapping("/register")
    public String registerDeliveryRuleName(@ModelAttribute DeliveryRuleNameRequest deliveryRuleNameRequest) {
        if (deliveryRuleNameService.createDeliveryRuleName(deliveryRuleNameRequest)) {
            return "redirect:/admin/delivery-rule-name";
        }
        return "redirect:/admin/delivery-rule-name/register";
    }

    @PostMapping("/delete")
    public String deleteDeliveryRuleName(@RequestParam String id) {
        deliveryRuleNameService.deleteDeliveryRuleName(id);
        return "";
    }
}
