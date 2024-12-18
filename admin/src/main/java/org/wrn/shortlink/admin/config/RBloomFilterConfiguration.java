package org.wrn.shortlink.admin.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Admin
 * @Desc:
 * @create: 2024-12-18 23:00
 **/
@Configuration
public class RBloomFilterConfiguration {

    @Bean
    public RBloomFilter<String> userRegisterCachePenetrationBloomFilter(RedissonClient redisson) {
        RBloomFilter<String> cachePenetrationBloomFilter = redisson.getBloomFilter("userRegisterCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L,0.001);
        return cachePenetrationBloomFilter;
    }
}
