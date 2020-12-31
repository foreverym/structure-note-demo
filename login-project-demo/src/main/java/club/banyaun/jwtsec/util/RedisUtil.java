package club.banyaun.jwtsec.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.xml.ws.soap.Addressing;
import java.util.concurrent.TimeUnit;

/**
 * @author : WangYB
 * @time: 2020/12/23  19:23
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 10, TimeUnit.MINUTES);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
