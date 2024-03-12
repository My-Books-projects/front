package store.mybooks.front.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * packageName    : store.mybooks.front.config <br/>
 * fileName       : RedisProperties<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/3/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/3/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "my-books.redis")
@RequiredArgsConstructor
public class RedisProperties {
    private String host;
    private int port;
    private String password;
    private int database;
    private Long adminExpiration;
}
