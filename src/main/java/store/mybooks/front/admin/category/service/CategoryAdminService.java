package store.mybooks.front.admin.category.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.category.adaptor.CategoryAdminAdaptor;
import store.mybooks.front.admin.category.model.request.CategoryCreateRequest;
import store.mybooks.front.admin.category.model.request.CategoryCreateRequestForTransmission;
import store.mybooks.front.admin.category.model.request.CategoryModifyRequest;
import store.mybooks.front.admin.category.model.request.CategoryModifyRequestForTransmission;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForBookCreate;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForUpdate;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForView;
import store.mybooks.front.admin.category.model.response.CategoryIdAndName;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.service
 * fileName       : CategorySservice
 * author         : damho-lee
 * date           : 2/24/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/24/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class CategoryAdminService {
    private final CategoryAdminAdaptor categoryAdminAdaptor;

    /**
     * methodName : getCategories <br>
     * author : damho-lee <br>
     * description : page, size 에 맞는 카테고리 리턴.<br>
     *
     * @param pageable Pageable
     * @return pageResponse
     */
    public PageResponse<CategoryGetResponseForView> getCategories(Pageable pageable) {
        PageResponse<CategoryGetResponseForView> pageResponse = categoryAdminAdaptor.getCategories(pageable);

        return new PageResponse<>(
                pageResponse.getContent(),
                pageResponse.getPageable(),
                pageResponse.isLast(),
                pageResponse.getTotalPages(),
                pageResponse.getTotalElements(),
                pageResponse.isFirst(),
                pageResponse.getSize(),
                pageResponse.getNumber(),
                pageResponse.getSort(),
                pageResponse.getNumberOfElements(),
                pageResponse.isEmpty()
        );
    }

    /**
     * methodName : getHighestCategories <br>
     * author : damho-lee <br>
     * description : 최상위 카테고리들의 id 와 이름만 가져온다.<br>
     *
     * @return list
     */
    public List<CategoryGetResponseForBookCreate> getCategories() {
        return categoryAdminAdaptor.getCategories();
    }


    /**
     * methodName : getHighestCategories <br>
     * author : damho-lee <br>
     * description : 최상위 카테고리들의 id 와 이름만 가져온다.<br>
     *
     * @return list
     */
    public List<CategoryIdAndName> getHighestCategories() {
        return categoryAdminAdaptor.getHighestCategories()
                .stream()
                .map(categoryGetResponse -> new CategoryIdAndName(categoryGetResponse.getId(),
                        categoryGetResponse.getName()))
                .collect(Collectors.toList());
    }

    /**
     * methodName : getChildCategories <br>
     * author : damho-lee <br>
     * description : category-register.html 에서 1단계 카테고리를 선택했을 때 2단계 카테고리 리스트를 가져오기 위한 메서드.<br>
     *
     * @param parentCategoryId ParentCategoryId
     * @return list
     */
    public List<CategoryIdAndName> getChildCategories(Integer parentCategoryId) {
        return categoryAdminAdaptor.getChildCategories(parentCategoryId)
                .stream()
                .map(categoryGetResponse -> new CategoryIdAndName(categoryGetResponse.getId(),
                        categoryGetResponse.getName()))
                .collect(Collectors.toList());
    }

    /**
     * methodName : createCategory <br>
     * author : damho-lee <br>
     * description : 카테고리 생성 메서드.<br>
     *
     * @param categoryCreateRequest CategoryCreateRequest
     */
    public void createCategory(CategoryCreateRequest categoryCreateRequest) {
        Integer parentCategoryId = categoryCreateRequest.getFirstParentCategoryId();
        if (categoryCreateRequest.getSecondParentCategoryId() != null) {
            parentCategoryId = categoryCreateRequest.getSecondParentCategoryId();
        }

        categoryAdminAdaptor.createCategory(new CategoryCreateRequestForTransmission(
                parentCategoryId,
                categoryCreateRequest.getName()
        ));
    }

    /**
     * methodName : updateCategory <br>
     * author : damho-lee <br>
     * description : 카테고리 수정 메서드.<br>
     *
     * @param categoryModifyRequest CategoryModifyRequest
     */
    public void updateCategory(CategoryModifyRequest categoryModifyRequest) {
        categoryAdminAdaptor.updateCategory(categoryModifyRequest.getId(), new CategoryModifyRequestForTransmission(
                categoryModifyRequest.getName()
        ));
    }

    /**
     * methodName : deleteCategory <br>
     * author : damho-lee <br>
     * description : 카테고리 삭제 메서드.<br>
     *
     * @param id Integer
     */
    public void deleteCategory(Integer id) {
        categoryAdminAdaptor.deleteCategory(id);
    }

    /**
     * methodName : getCategory <br>
     * author : damho-lee <br>
     * description : id 로 수정하려는 Category 를 찾아서 리턴. 1단계, 2단계 카테고리를 찾음.<br>
     *
     * @param id Integer
     * @return CategoryGetResponseForUpdate get response for update
     */
    public CategoryGetResponseForUpdate getCategory(Integer id) {
        return categoryAdminAdaptor.getCategory(id);
    }
}
