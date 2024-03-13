package store.mybooks.front.admin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.auth.Annotation.RequiredAdminCookie;

/**
 * packageName    : store.mybooks.front.admin.controller<br>
 * fileName       : AdminController<br>
 * author         : minsu11<br>
 * date           : 2/22/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/22/24        minsu11       최초 생성<br>
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * get 요청이 들어오면 관리자 페이지를 반환
     *
     * @return 관리자 페이지 파일 명
     */

    @GetMapping
    @RequiredAdminCookie
    public String doAdmin() {
        return "admin";
    }
}
