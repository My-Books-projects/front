package store.mybooks.front.auth.redis;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : store.mybooks.front.auth.redis<br>
 * fileName       : RedisService<br>
 * author         : masiljangajji<br>
 * date           : 3/12/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/12/24        masiljangajji       최초 생성
 */
@Service
@RequiredArgsConstructor
public class RedisAuthService {

    private final RedisTemplate<String, Object> redisTemplate;

    // Duration 시간동안만 유지되고 후에는 삭제
    public void setValues(String key, String data, Duration duration) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data, duration);
    }

    // 값 찾아오기 , 없으면 false;
    @Transactional(readOnly = true)
    public String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) {
            return null;
        }
        return (String) values.get(key);
    }

    // 삭제함
    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }

    public void expireValues(String key, Long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

}