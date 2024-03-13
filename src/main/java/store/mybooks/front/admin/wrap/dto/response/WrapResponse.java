package store.mybooks.front.admin.wrap.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.wrap.dto.response<br>
 * fileName       : WrapResponse<br>
 * author         : minsu11<br>
 * date           : 2/29/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/29/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class WrapResponse {
    private String name;
    private Integer cost;
    private Boolean isAvailable;


}
