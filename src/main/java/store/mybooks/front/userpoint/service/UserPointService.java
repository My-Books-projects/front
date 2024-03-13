package store.mybooks.front.userpoint.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.userpoint.adaptor.UserPointAdaptor;
import store.mybooks.front.userpoint.dto.response.PointResponse;

/**
 * packageName    : store.mybooks.front.userpoint.service<br>
 * fileName       : UserPointService<br>
 * author         : minsu11<br>
 * date           : 3/10/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/10/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class UserPointService {
    private final UserPointAdaptor userPointAdaptor;

    public PointResponse getPointsHeld() {
        return userPointAdaptor.getPointsHeld();

    }
}
