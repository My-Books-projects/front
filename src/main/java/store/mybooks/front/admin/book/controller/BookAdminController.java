package store.mybooks.front.admin.book.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.admin.author.dto.response.AuthorGetResponse;
import org.springframework.web.multipart.MultipartFile;
import store.mybooks.front.admin.author.service.AuthorService;
import store.mybooks.front.admin.book.model.request.BookCreateRequest;
import store.mybooks.front.admin.book.model.request.BookModifyRequest;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.book.service.BookAdminService;
import store.mybooks.front.admin.category.model.response.CategoryIdAndName;
import store.mybooks.front.admin.category.service.CategoryAdminService;
import store.mybooks.front.admin.publisher.service.PublisherService;
import store.mybooks.front.admin.tag.model.response.TagGetResponseForBookDetail;
import store.mybooks.front.admin.tag.service.TagService;

/**
 * packageName    : store.mybooks.front.admin.book.controller <br/>
 * fileName       : BookAdminController<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/book")
public class BookAdminController {
    private final BookAdminService bookAdminService;
    private final PublisherService publisherService;
    private final CategoryAdminService categoryAdminService;
    private final TagService tagService;
    private final AuthorService authorService;

    /**
     * methodName : getBookPage
     * author : newjaehun
     * description : 사이드바에서 책을 눌렀을 때 페이지.
     *
     * @param pageable Pageable
     * @param model    Model
     * @return string
     */
    @GetMapping
    public String getBookPage(@PageableDefault(size = 8) Pageable pageable, Model model) {
        model.addAttribute("books", bookAdminService.getBooks(pageable));
        return "admin/view/book/book";
    }

    /**
     * methodName : getBookRegisterPage
     * author : newjaehun
     * description : 도서 등록 페이지.
     *
     * @param model Model
     * @return string
     */
    @GetMapping("/register")
    public String getBookRegisterPage(Model model) {
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("categories", categoryAdminService.getCategories());
        model.addAttribute("tags", tagService.getTags());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("bookStatuses", bookAdminService.getBookStatus());
        return "admin/view/book/book-register";
    }

    /**
     * methodName : createBook
     * author : newjaehun
     * description : 도서 등록 요청 받는 메소드.
     *
     * @param bookCreateRequest BookCreateRequest
     * @return string
     */
    @PostMapping("/register")
    public String createBook(@Valid @ModelAttribute BookCreateRequest bookCreateRequest, @RequestParam("thumbnailImage")
    MultipartFile thumbnailImage, @RequestParam("contentImage") List<MultipartFile> contentImages) throws IOException {
        bookAdminService.createBook(bookCreateRequest, thumbnailImage, contentImages);
        return "redirect:/admin/book/register";
    }

    /**
     * methodName : getBookUpdatePage
     * author : newjaehun
     * description : 도서 업데이트 페이지.
     *
     * @param bookId BookId
     * @param model  Model
     * @return string
     */
    @GetMapping("/update")
    public String getBookUpdatePage(@RequestParam("id") Long bookId, Model model) {
        BookDetailResponse book = bookAdminService.getBook(bookId);
        model.addAttribute("book", book);
        model.addAttribute("bookAuthorList", book.getAuthorList().stream().map(AuthorGetResponse::getId).collect(Collectors.toList()));
        model.addAttribute("bookTagList",
                book.getTagList().stream().map(TagGetResponseForBookDetail::getId).collect(Collectors.toList()));
        model.addAttribute("bookCategoryList", book.getCategoryList().stream().map(CategoryIdAndName::getId).collect(Collectors.toList()));

        model.addAttribute("categories", categoryAdminService.getCategories());
        model.addAttribute("tags", tagService.getTags());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("bookStatuses", bookAdminService.getBookStatus());
        return "admin/view/book/book-update";
    }

    /**
     * methodName : updateBook
     * author : newjaehun
     * description : 도서 업데이트 요청.
     *
     * @param modifyRequest ModifyRequest
     * @return string
     */
    @PostMapping("/update/{id}")
    public String updateBook(@RequestParam("id") Long bookId, @ModelAttribute BookModifyRequest modifyRequest) {
        bookAdminService.updateBook(bookId, modifyRequest);
        return "redirect:/admin/book/update?id=" + bookId;
    }
}
