package store.mybooks.front.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.authorization.jwt.dto.request<br>
 * fileName       : LogoutRequest<br>
 * author         : masiljangajji<br>
 * date           : 3/8/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/8/24        masiljangajji       최초 생성
 */

@Getter
@AllArgsConstructor
public class LogoutRequest {

    private String accessToken;

    private String ip;

    private String userAgent;

}
