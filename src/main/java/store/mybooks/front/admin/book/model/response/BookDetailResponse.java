package store.mybooks.front.admin.book.model.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.mybooks.front.admin.author.dto.response.AuthorGetResponse;
import store.mybooks.front.admin.category.model.response.CategoryIdAndName;
import store.mybooks.front.admin.publisher.dto.response.PublisherGetResponse;
import store.mybooks.front.admin.tag.model.response.TagGetResponseForBookDetail;
import store.mybooks.front.image.dto.ImageResponse;

/**
 * packageName    : store.mybooks.front.admin.book.model.response <br/>
 * fileName       : BookDetailResponse<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
public class BookDetailResponse {
    private Long id;

    private ImageResponse thumbNailImage;

    private String name;

    private String bookStatus;

    private List<AuthorGetResponse> authorList;

    private PublisherGetResponse publisher;

    private LocalDate publishDate;

    private Integer saleCost;

    private Integer originalCost;

    private Integer disCountRate;

//    private Double totalRate;

//    private Integer reviewCount;

    private Integer likeCount;

    private Boolean isPacking;

    private Integer page;

    private String isbn;

    private List<CategoryIdAndName> categoryList;

    private List<TagGetResponseForBookDetail> tagList;

    private Integer stock;

    private String index;

    private String explanation;

    private List<ImageResponse> contentImageList;

//    private List<ReviewDetailResponse> review;


}