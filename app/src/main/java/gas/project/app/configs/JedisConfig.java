package gas.project.app.configs;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class JedisConfig {
    @Value("${var.ip.redis}")
    private String redisHost;

    @Value("${var.port.redis}")
    private int redisPort;

    @Bean
    public JedisPool jedisPool() {
        GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(10);  // Tamanho máximo do pool de conexões

        // Criação do JedisPool
        return new JedisPool(poolConfig, redisHost, redisPort); // Endereço do Redis no Docker
    }

    @Bean
    public Jedis jedis(JedisPool jedisPool) {
        return jedisPool.getResource();
    }
}

