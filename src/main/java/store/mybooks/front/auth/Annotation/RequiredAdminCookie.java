package store.mybooks.front.auth.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName    : store.mybooks.front.auth.Annotation<br>
 * fileName       : RequiredAdminAuthorization<br>
 * author         : masiljangajji<br>
 * date           : 3/12/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/12/24        masiljangajji       최초 생성
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredAdminCookie {



}
