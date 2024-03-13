package store.mybooks.front.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import store.mybooks.front.utils.CookieUtils;

/**
 * packageName    : store.mybooks.front.auth<br>
 * fileName       : CookieInterceptor<br>
 * author         : masiljangajji<br>
 * date           : 3/5/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/5/24        masiljangajji       최초 생성
 */


public class CookieInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        System.out.println("요청 URI: " + request.getRequestURI());
        request.setAttribute("identity_cookie_value", CookieUtils.getIdentityCookieValue(request));
        request.setAttribute("admin_cookie_value", CookieUtils.getAdminCookieValue(request));

        return true; // 요청 계속 진행
    }


}