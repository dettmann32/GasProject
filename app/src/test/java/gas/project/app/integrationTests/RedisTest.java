package gas.project.app.integrationTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import redis.clients.jedis.Jedis;

@SpringBootTest
class RedisTest{

    @Autowired
    private Jedis jedis;

    @Test
    @DisplayName("Should create a value and key")
    @Transactional
    void should_Create_a_value_and_key() {
        jedis.set("key", "value");
        String value = jedis.get("key");

        Assertions.assertThat(value).isEqualTo("value");

        jedis.del("key");

    }

    

}
  