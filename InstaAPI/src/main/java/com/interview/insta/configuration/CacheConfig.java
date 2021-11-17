package com.interview.insta.configuration;

// @Configuration
// //@EnableRedisRepositories
// @EnableCaching
public class CacheConfig {

    // @Bean
    // public JedisConnectionFactory connectionFactory() {
    //     RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    //     configuration.setHostName("localhost");
    //     configuration.setPort(6379);
    //     return new JedisConnectionFactory(configuration);
    // }

    // @Bean
    // public RedisTemplate<String, Object> template() {
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(connectionFactory());
    //     template.setKeySerializer(new StringRedisSerializer());
    //     template.setHashKeySerializer(new StringRedisSerializer());
    //     template.setHashKeySerializer(new JdkSerializationRedisSerializer());
    //     template.setValueSerializer(new JdkSerializationRedisSerializer());
    //     template.setEnableTransactionSupport(true);
    //     template.afterPropertiesSet();
    //     return template;
    // }

    // @Bean
    // public CacheManager cacheManager(){
    //     return new ConcurrentMapCacheManager();
    // }

}
