package store.mybooks.front.global;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import store.mybooks.front.auth.exception.AccessIdForbiddenException;
import store.mybooks.front.auth.exception.AuthenticationIsNotValidException;
import store.mybooks.front.auth.exception.StatusIsDormancyException;
import store.mybooks.front.auth.exception.StatusIsLockException;
import store.mybooks.front.auth.exception.TokenExpiredException;

/**
 * packageName    : store.mybooks.front.global
 * fileName       : GlobalControllerAdvice
 * author         : damho-lee
 * date           : 2/27/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/27/24          damho-lee          최초 생성
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private static final String REFERER = "referer";

    private static final String domain = "http://localhost:8080";

    /**
     * methodName : badRequestException <br>
     * author : damho-lee <br>
     * description : resource 서버에서 BadRequest, NotFound 상태코드가 오는 경우를 처리하는 ExceptionHandler.<br>
     *
     * @param exception HttpClientErrorException
     * @return string
     */
    @ExceptionHandler({HttpClientErrorException.BadRequest.class, HttpClientErrorException.NotFound.class})
    // 400 404 이게 리소스에서 나오는 모든 예외
    public String handleBadRequestAndNotFoundException(Exception exception, HttpServletRequest request) {

        String previousUrl = request.getHeader(REFERER);
        request.getSession().setAttribute("error", exception.getMessage());
        return previousUrl.replace(domain, "redirect:");
    }

    // 토큰 인증/인가와 관련된 모든 예외를 잡음
    @ExceptionHandler({AuthenticationIsNotValidException.class, AccessIdForbiddenException.class,
            StatusIsDormancyException.class, TokenExpiredException.class, StatusIsLockException.class})
    public String handleAuthException(RuntimeException ex) {

        if (ex instanceof AuthenticationIsNotValidException | ex instanceof TokenExpiredException) {
            return "redirect:/login"; // 토큰조작 됐거나 , 만료됐음 -> 다시 로그인
        } else if (ex instanceof StatusIsDormancyException) {
            return "redirect:/verification/dormancy";  // 유저계정 휴면상태
        } else if (ex instanceof StatusIsLockException) {
            return "redirect:/verification/lock"; // 유정계정 잠금상태
        }

        // 권한없는 경우 index
        return "redirect:/";
    }


}
