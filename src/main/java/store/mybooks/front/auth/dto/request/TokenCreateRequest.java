package store.mybooks.front.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.jwt.dto.request<br>
 * fileName       : TokenRequest<br>
 * author         : masiljangajji<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/28/24        masiljangajji       최초 생성
 */

@Getter
@AllArgsConstructor
public class TokenCreateRequest {

    private Boolean isAdmin;

    private Long userId;

    private String status;

    private String uuid;

    private String ip;

    private String userAgent;

}
