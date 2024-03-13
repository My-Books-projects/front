package store.mybooks.front.cart.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.cart <br/>
 * fileName       : CartDetail<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/3/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/3/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartDetail {
    private Long bookId;
    private int cartDetailAmount;
    private String name;
    private String bookImage;
    private Integer saleCost;

    public void amountUpdate(int amount) {
        this.cartDetailAmount = this.cartDetailAmount + amount;
    }

}
