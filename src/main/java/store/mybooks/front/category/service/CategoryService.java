package store.mybooks.front.category.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.category.adaptor.CategoryAdaptor;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForMainView;

/**
 * packageName    : store.mybooks.front.category.service
 * fileName       : CategoryService
 * author         : damho-lee
 * date           : 3/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/13/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryAdaptor categoryAdaptor;

    public List<CategoryGetResponseForMainView> getCategoriesForMainView() {
        return categoryAdaptor.getCategoriesForMainView();
    }
}
