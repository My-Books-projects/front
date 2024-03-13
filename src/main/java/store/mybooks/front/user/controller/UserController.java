package store.mybooks.front.user.controller;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import store.mybooks.front.auth.adaptor.TokenAdaptor;
import store.mybooks.front.auth.dto.request.TokenCreateRequest;
import store.mybooks.front.auth.dto.response.TokenCreateResponse;
import store.mybooks.front.auth.redis.RedisAuthService;
import store.mybooks.front.config.RedisProperties;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.request.UserCreateRequest;
import store.mybooks.front.user.dto.request.UserEmailRequest;
import store.mybooks.front.user.dto.request.UserGradeModifyRequest;
import store.mybooks.front.user.dto.request.UserLoginRequest;
import store.mybooks.front.user.dto.request.UserModifyRequest;
import store.mybooks.front.user.dto.request.UserPasswordModifyRequest;
import store.mybooks.front.user.dto.request.UserStatusModifyRequest;
import store.mybooks.front.user.dto.response.UserEncryptedPasswordResponse;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.user.dto.response.UserLoginResponse;
import store.mybooks.front.utils.CookieUtils;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.user.controller<br>
 * fileName       : UserController<br>
 * author         : masiljangajji<br>
 * date           : 2/23/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/23/24        masiljangajji       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserAdaptor userAdaptor;

    private final TokenAdaptor tokenAdaptor;

    private final PasswordEncoder passwordEncoder;

    private final RedisAuthService redisAuthService;

    private final RedisProperties redisProperties;


    /**
     * methodName : loginUserForm
     * author : masiljangajji
     * description : 로그인 페이지로 이동
     *
     * @return string
     */
    @GetMapping("/login")
    public String loginUserForm(HttpServletRequest request) {

        if (Objects.isNull(request.getAttribute("identity_cookie_value"))) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/auth/payco/login")
    public RedirectView paycoLoginForm() {

        return new RedirectView(
                "https://id.payco.com/oauth2.0/authorize?response_type=code&client_id=3RDktHA_exycIbutIzVLP3D&serviceProviderCode=FRIENDS&redirect_uri=https://www.my-books.store/login/oauth2/code/payco&state=ab42ae&userLocale=ko_KR");
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }


    /**
     * methodName : createUserForm
     * author : masiljangajji
     * description : 회원가입 페이지로 이동
     *
     * @return string
     */
    @GetMapping("/signup")
    public String createUserForm(HttpServletRequest request) {
        if (Objects.isNull(request.getAttribute("identity_cookie_value"))) {
            return "signup";
        }
        return "redirect:/";
    }

    // 휴면계정인 사람 휴면인증 페이지로
    @GetMapping("/verification/dormancy")
    public String dormancyUserForm() {
        return "dormancy";
    }

    // 휴면계정 dooray 인증 받겠다.
    @PostMapping("/dormancy")
    public String verifyDormancyUser() {
        userAdaptor.verifyDormancyUser();
        return "redirect:/";
    }

    @GetMapping("/verification/lock")
    public String lockUserForm() {
        return "lock";
    }

    @PostMapping("/lock")
    public String verifyLockUser(@ModelAttribute UserPasswordModifyRequest request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userAdaptor.verifyLockUser(request);
        return "redirect:/";
    }


    /**
     * methodName : myPageForm
     * author : masiljangajji
     * description : mypage로 이동
     *
     * @param model 유저의 정보
     * @return string
     */
    @GetMapping("/user")
    public String myPageForm(Model model) {

        UserGetResponse userGetResponse = userAdaptor.findUser();
        model.addAttribute("user", userGetResponse);
        return "my-page";
    }

    /**
     * methodName : loginUser
     * author : masiljangajji
     * description : 유저의 로그인처리
     *
     * @param userLoginRequest login request
     * @return string
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginRequest userLoginRequest, HttpServletRequest request,
                            HttpServletResponse response) {

        UserEmailRequest emailRequest = new UserEmailRequest(userLoginRequest.getEmail());

        // 이메일 존재하는지 , 탈퇴했는지
        UserEncryptedPasswordResponse userEncryptedPasswordResponse =
                userAdaptor.verifyUserStatus(emailRequest);

        // 비밀번호도 같으면 완료
        if (passwordEncoder.matches(userLoginRequest.getPassword(),
                userEncryptedPasswordResponse.getEncryptedPassword())) {

            UserLoginResponse loginResponse = userAdaptor.completeLoginProcess(emailRequest);

            if (loginResponse.getIsAdmin()) {
                String adminCookieValue = String.valueOf(UUID.randomUUID());
                redisAuthService.setValues(adminCookieValue,
                        Utils.getUserIp(request) + Utils.getUserAgent(request), Duration.ofMillis(
                                redisProperties.getAdminExpiration()));
                CookieUtils.addAdminCookie(response, adminCookieValue);
            }
            TokenCreateResponse tokenCreateResponse =
                    tokenAdaptor.createToken(
                            new TokenCreateRequest(loginResponse.getIsAdmin(), loginResponse.getUserId(),
                                    loginResponse.getStatus(), String.valueOf(UUID.randomUUID()),Utils.getUserIp(request),Utils.getUserAgent(request)));

            // 쿠키추가
            CookieUtils.addJwtCookie(response, tokenCreateResponse.getAccessToken());
            return "redirect:/";
        }

        return "redirect:/login";
    }


    /**
     * methodName : createUser
     * author : masiljangajji
     * description : 회원가입 요청을 처리
     *
     * @param userCreateRequest create request
     * @return string
     */
    @PostMapping("/signup")
    public String createUser(@ModelAttribute UserCreateRequest userCreateRequest) {

        // 비밀번호 암호화
        userCreateRequest.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

        userAdaptor.createUser(userCreateRequest);
        return "redirect:/";
    }

    /**
     * methodName : modifyUserPassword
     * author : masiljangajji
     * description : 유저의 비밀번호 변경
     *
     * @param modifyRequest request
     * @return string
     */
    @PostMapping("/user/modify/password")
    public String modifyUserPassword(@ModelAttribute UserPasswordModifyRequest modifyRequest) {


        // 비밀번호 암호화
        modifyRequest.setPassword(passwordEncoder.encode(modifyRequest.getPassword()));
        userAdaptor.modifyUserPassword(modifyRequest);
        return "redirect:/";
    }

    /**
     * methodName : modifyUserStatus
     * author : masiljangajji
     * description : 유저의 상태변경
     *
     * @param modifyRequest request
     * @return string
     */
    @PostMapping("/user/{userId}/modify/status")
    public String modifyUserStatus(@PathVariable(name = "userId") Long userId,
                                   @ModelAttribute UserStatusModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserStatus(userId, modifyRequest);
        return "redirect:/user";
    }

    /**
     * methodName : modifyUserGrade
     * author : masiljangajji
     * description : 유저의 등급 변경
     *
     * @param modifyRequest request
     * @return string
     */
    @PostMapping("/user/{userId}/modify/grade")
    public String modifyUserGrade(@PathVariable(name = "userId") Long userId,
                                  @ModelAttribute UserGradeModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserGrade(userId, modifyRequest);
        return "redirect:/user";
    }


    /**
     * methodName : modifyUser
     * author : masiljangajji
     * description : 유저의 정보를 변경 (이름,전화번호)
     *
     * @param modifyRequest request
     * @return string
     */
    @PostMapping("/user/modify")
    public String modifyUser(@ModelAttribute UserModifyRequest modifyRequest) {
        userAdaptor.modifyUser(modifyRequest);
        return "redirect:/user";
    }


    /**
     * methodName : deleteUser
     * author : masiljangajji
     * description : 회원탈퇴
     *
     * @return string
     */
    @PostMapping("/user/delete")
    public String deleteUser() {
        userAdaptor.deleteUser();
        return "redirect:/";
    }


}