package store.mybooks.front.book.author.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.book.author.dto.request.AuthorCreateRequest;
import store.mybooks.front.book.author.dto.request.AuthorDeleteRequest;
import store.mybooks.front.book.author.dto.request.AuthorModifyRequest;
import store.mybooks.front.book.author.dto.response.AuthorResponse;
import store.mybooks.front.book.author.service.AuthorService;

/**
 * packageName    : store.mybooks.front.book.author.controller<br>
 * fileName       : AuthorController<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/authors")
public class AuthorController {
    private final AuthorService authorService;


    /**
     * methodName : viewAuthor<br>
     * author : minsu11<br>
     * description : 모든 저자를 조회한 {@code list}를 {@code view}에 보야줌.
     * <br> *
     *
     * @param modelMap 모든 저자의 {@code list}
     * @return 모든 저자의 {@code list}를 보여주는 {@code view}의 경로
     */
    @GetMapping
    public String viewAuthor(ModelMap modelMap) {
        log.info("저자 폼");

        List<AuthorResponse> authorResponseList = authorService.getAllAuthors();
        modelMap.put("authorList", authorResponseList);
        // todo 관리자 권한이 있는 사람만 가능하게
        return "admin/view/author-view";
    }

    /**
     * methodName : viewAuthorRegister<br>
     * author : minsu11<br>
     * description : 저자 등록 {@code view}로 이동
     * <br> *
     *
     * @return string
     */
    @GetMapping("/register")
    public String viewAuthorRegister() {

        return "admin/view/author-register-view";
    }


    /**
     * methodName : doRegisterAuthor<br>
     * author : minsu11<br>
     * description : 저자 등록. 등록 성공 시 저자 목록 {@code view}를 보여주고, 실패 시 등록 창을 다시 보여줌
     * <br> *
     *
     * @param createRequest 등록할 저자 정보
     * @return {@code view}의 경로
     */
    @PostMapping("/register")
    public String doRegisterAuthor(
            @ModelAttribute AuthorCreateRequest createRequest) {

        log.info("=======>value: {}", createRequest.getName());
        if (authorService.createAuthor(createRequest)) {
            log.info("확인");
            return "redirect:/admin/authors";
        }
        return "redirect:/admin/authors/register";
    }

    /**
     * methodName : viewModifyForm<br>
     * author : minsu11<br>
     * description : 수정 요청 시 수정 {@code view}를 보여주는 메서드
     * <br> *
     *
     * @param modelMap 수정할 저자 데이터를 view에 전달해주는 modelMap
     * @param id       저자의 id
     * @return {@code view}가 저장된 경로
     */
    @GetMapping("/update")
    public String viewModifyForm(ModelMap modelMap,
                                 @RequestParam(name = "id") Integer id) {
        log.info("author id value: {}", id);
        modelMap.put("modifyAuthor", new AuthorModifyRequest(1, "test", "ts"));
        return "admin/view/author-register-view";
    }

    /**
     * methodName : doModifyAuthor<br>
     * author : minsu11<br>
     * description : 저자 수정 기능. 수정이 정상 완료가 되면은 저자 리스트를 보여주는 {@code view} 반환.
     * 수정이 실패가 되면은 수정하는 {@code view} 반환
     * <br> *
     *
     * @param modelMap 수정 실패 시 수정 값을 view에 보여주긴 위한 {@code modelMap}
     * @param request  수정할 저자가 들어있는 dto
     * @return string
     */
    @PostMapping("/update")
    public String doModifyAuthor(
            ModelMap modelMap,
            @ModelAttribute AuthorModifyRequest request) {

        log.info("modify request: {}", request);
        if (authorService.updateAuthor(request)) {
            log.info("if문 진입");
            return "redirect:/admin/authors";
        }
        modelMap.put("modifyAuthor", request);
        return "redirect:/admin/authors/modify";
    }

    /**
     * methodName : doDeleteAuthor<br>
     * author : minsu11<br>
     * description : 삭제 버튼 클릭 시 해당 저자의 정보를 삭제
     * <br> *
     *
     * @param id 삭제할 저자의 {@code id}
     * @return 삭제한 뒤 보여줄 {@code view}의 경로
     */
    @PostMapping("/delete")
    public String doDeleteAuthor(@ModelAttribute AuthorDeleteRequest id) {
        log.info("삭제할 id value: {}", id);
        authorService.deleteAuthor(id);

        return "redirect:/admin/authors";
    }
}
