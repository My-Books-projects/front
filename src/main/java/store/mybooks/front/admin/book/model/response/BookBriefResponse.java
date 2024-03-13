package store.mybooks.front.admin.book.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import store.mybooks.front.image.dto.ImageResponse;

/**
 * packageName    : store.mybooks.front.admin.book.model.response <br/>
 * fileName       : BookBriefResponse<br/>
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
public class BookBriefResponse {
    private Long id;

    private ImageResponse imageResponse;

    private String name;

//    private Double rate;

    private Integer saleCost;
}
