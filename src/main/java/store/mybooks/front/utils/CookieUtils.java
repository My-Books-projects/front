package store.mybooks.front.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import store.mybooks.front.config.CookieConfig;


/**
 * packageName    : store.mybooks.front.utils<br>
 * fileName       : CookieUtils<br>
 * author         : masiljangajji<br>
 * date           : 3/5/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/5/24        masiljangajji       최초 생성
 */


@Component
@RequiredArgsConstructor
public class CookieUtils {

    private static CookieConfig cookieConfig = null;

    @Autowired
    public CookieUtils(CookieConfig cookieConfig) {
        CookieUtils.cookieConfig = cookieConfig;
    }

    public static void addJwtCookie(HttpServletResponse response, String token) {

        final ResponseCookie responseCookie = ResponseCookie
                .from("identity_cookie", token)
                .secure(true)
                .httpOnly(true)
                .domain(cookieConfig.getDomain())
                .path("/")
                .maxAge(3 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    public static void addAdminCookie(HttpServletResponse response, String adminCookieValue) {

        final ResponseCookie responseCookie = ResponseCookie
                .from("admin_cookie", adminCookieValue)
                .secure(true)
                .httpOnly(true)
                .domain(cookieConfig.getDomain())
                .path("/")
                .maxAge(3 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    public static void deleteJwtCookie(HttpServletResponse response) {

        final ResponseCookie responseCookie = ResponseCookie
                .from("identity_cookie", "")
                .domain(cookieConfig.getDomain())
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    public static void deleteAdminCookie(HttpServletResponse response) {

        final ResponseCookie responseCookie = ResponseCookie
                .from("admin_cookie", "")
                .domain(cookieConfig.getDomain())
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

    }

    public static String getIdentityCookieValue(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("identity_cookie".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String getAdminCookieValue(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("admin_cookie".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
