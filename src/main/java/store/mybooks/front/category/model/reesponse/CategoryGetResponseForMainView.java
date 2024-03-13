package store.mybooks.front.category.model.reesponse;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.mybooks.front.admin.category.model.response.CategoryIdAndName;

/**
 * packageName    : store.mybooks.front.category.model.reesponse
 * fileName       : CategoryGetResponseForMainView
 * author         : damho-lee
 * date           : 3/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/13/24          damho-lee          최초 생성
 */
@Getter
@NoArgsConstructor
public class CategoryGetResponseForMainView {
    private Integer parentCategoryId;
    private String parentCategoryName;
    private List<CategoryIdAndName> childCategoryList;
}
