package store.mybooks.front.book.author.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.book.author.adaptor.AuthorAdaptor;
import store.mybooks.front.book.author.dto.request.AuthorCreateRequest;
import store.mybooks.front.book.author.dto.response.AuthorResponse;

/**
 * packageName    : store.mybooks.front.book.author.service<br>
 * fileName       : AuthorService<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    : 응답 받은 저자 데이터를 가공해서 view에 보여주는 service
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorAdaptor adaptor;


    /**
     * methodName : getAllAuthors<br>
     * author : minsu11<br>
     * description : 조회된 저자의 모든 데이터를 반환
     * <br> *
     *
     * @return 모든 저자의 list
     */
    public List<AuthorResponse> getAllAuthors() {
        return adaptor.getAuthor().getContent();
    }

    /**
     * methodName : createAuthor<br>
     * author : minsu11<br>
     * description : 등록할 저자의 정보를 저장.
     * 저장에 성공 되면 {@code true} 반환. 실패 하면 {@code false} 반환
     * <br> *
     *
     * @param request 등록할 저자 정보
     * @return 저장한 객체의 응답 값이 {@code nonNull}인지 아닌지 따른 {@code boolean} 값 반환
     */
    public boolean createAuthor(AuthorCreateRequest request) {
        return Objects.nonNull(adaptor.createAuthor(request));
    }
}
