package com.bennyrhys.cache.config;

import com.bennyrhys.cache.bean.Employee;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration//这个千万别忘
public class MyredisConfig {
    @Bean
    @ConditionalOnMissingBean(
            name = {"redisTemplate"}
    )

    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //导入要转化的serializer json
        Jackson2JsonRedisSerializer<Employee> ser = new Jackson2JsonRedisSerializer<Employee>(Employee.class);

        //自己放一个序列化器
        template.setDefaultSerializer(ser);
        return template;
    }

    /**
     * 然而在springboot2.x中，RedisCacheManager已经没有了单参数的构造方法
     *本人没有看1.x的源码，我发现这里有一个RedisCacheManagerBuilder的内部类,
     * 从名字就不难发现这是一个用来构造RedisCacheManager的建造模式的应用吧。
     * 所以以下是本人使用的RedisCacheManager的构造方法（如果定制化要求的话可以修改其中的配置就可以）.
     * 这里我只设置了缓存失效时间为一小时，如需其他配置可以到RedisCacheConfiguration这个类中去寻找一下。
     * @return
     *
     *
     *
     *修改配置
     *     @Bean
     *     public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
     *         RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
     *                 .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
     *         return RedisCacheManager
     *                 .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
     *                 .cacheDefaults(redisCacheConfiguration).build();
     *     }
     *
     *配置json
     *@Configuration
     * @ConfigurationProperties(prefix = "spring.cache.redis")
     * public class RedisCacheConfig {
     *
     *     private Duration timeToLive = Duration.ZERO;
     *     public void setTimeToLive(Duration timeToLive) {
     *         this.timeToLive = timeToLive;
     *     }
     *
     *     @Bean
     *     public CacheManager cacheManager(RedisConnectionFactory factory) {
     *         RedisSerializer<String> redisSerializer = new StringRedisSerializer();
     *         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
     *
     *         //解决查询缓存转换异常的问题
     *         ObjectMapper om = new ObjectMapper();
     *         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
     *         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
     *         jackson2JsonRedisSerializer.setObjectMapper(om);
     *
     *         // 配置序列化（解决乱码的问题）
     *         RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
     *                 .entryTtl(timeToLive)
     *                 .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
     *                 .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
     *                 .disableCachingNullValues();
     *
     *         RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
     *                 .cacheDefaults(config)
     *                 .build();
     *         return cacheManager;
     *     }
     *
     * }
     *
     *
     *
     */
/*    @Bean
    public RedisCacheManager empRedisCacheManager(RedisTemplate<Object, Employee> empRedisTemplate){
        RedisCacheManager cm = new RedisCacheManager(this.cacheWriter, this.defaultCacheConfiguration, this.initialCaches, this.allowInFlightCacheCreation);
        cm.setTransactionAware(this.enableTransactions);
        return cm;
    }*/

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(timeToLive)//后来在加时间
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }



//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//
//    }
}
