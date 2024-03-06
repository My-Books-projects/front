package store.mybooks.front.home;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : store.mybooks.front
 * fileName       : HomeController
 * author         : masiljangajji
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24        masiljangajji       최초 생성
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

}
