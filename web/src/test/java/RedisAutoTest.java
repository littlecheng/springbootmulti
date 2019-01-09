import com.example.Application;
import com.example.RedisCfg;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <b>类中文名</b>
 * <p>类的描述，主要描述类的作用，使用说明，重要物性</p>
 *
 * @author 程涛(88396208)
 * @date 2018-12-24
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Component
public class RedisAutoTest {
    @Autowired
    private RedisCfg redisCfg;
    @Test
    public void propsTest() throws JsonProcessingException {
        System.out.println("simpleProp: " + redisCfg.getHost());
        System.out.println("MaxIdle: " + redisCfg.getMaxIdle());
        System.out.println("MaxWaitMillis: " + redisCfg.getMaxWaitMillis());
        System.out.println("Password: " + redisCfg.getPassword());
        System.out.println("Timeout: " + redisCfg.getTimeout());
        System.out.println("port: " + redisCfg.getPort());

        JedisPool jedisPool = redisCfg.redisPoolFactory();
        Jedis jedis = jedisPool.getResource();
        jedis.set("name","jacky");
        jedis.set("age","20");

        System.out.println(jedis.get("name"));
        System.out.println(jedis.get("age"));

    }



}
