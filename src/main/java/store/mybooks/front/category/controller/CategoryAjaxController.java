package store.mybooks.front.category.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForMainView;
import store.mybooks.front.category.service.CategoryService;

/**
 * packageName    : store.mybooks.front.category.controller
 * fileName       : CategoryAjaxController
 * author         : damho-lee
 * date           : 3/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/13/24          damho-lee          최초 생성
 */
@RestController
@RequestMapping("/ajax/category")
@RequiredArgsConstructor
public class CategoryAjaxController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryGetResponseForMainView> getCategoriesForHeader() {
        return categoryService.getCategoriesForMainView();
    }
}
