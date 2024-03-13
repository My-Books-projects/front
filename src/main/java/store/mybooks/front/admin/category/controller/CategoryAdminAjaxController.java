package store.mybooks.front.admin.category.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.admin.category.model.response.CategoryIdAndName;
import store.mybooks.front.admin.category.service.CategoryAdminService;

/**
 * packageName    : store.mybooks.front.category.controller
 * fileName       : CategoryAjaxController
 * author         : damho-lee
 * date           : 2/24/24
 * description    : Ajax 를 이용한 비동기 처리를 위한 RestController.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/24/24          damho-lee          최초 생성
 */
@RestController
@RequestMapping(("/admin/ajax/category"))
@RequiredArgsConstructor
public class CategoryAdminAjaxController {
    private final CategoryAdminService categoryAdminService;

    /**
     * methodName : getChildCategories <br>
     * author : damho-lee <br>
     * description : 1단계 카테고리를 고르면 해당 1단계 카테고리를 부모로 가지는 자식 카테고리들을 불러온다. 없음을 선택한 경우 2단계 카테고리를 비운다.<br>
     *
     * @param parentCategoryId Integer
     * @return list
     */
    @GetMapping
    public List<CategoryIdAndName> getChildCategories(
            @RequestParam(value = "parentCategoryId", required = false) Integer parentCategoryId) {
        return parentCategoryId == null ? null : categoryAdminService.getChildCategories(parentCategoryId);
    }
}
