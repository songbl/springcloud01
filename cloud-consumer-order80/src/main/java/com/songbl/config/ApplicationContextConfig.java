package com.songbl.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Compent 和@Bean 区别在哪里：
 *   在应用开发中，如果想要将第三方中的组件装配到你的应用中，在这种情况下，是没有办法在第三方组件上添加@Component注解的，因此不能使用自动装配方案
 *
 */
@Configuration
public class ApplicationContextConfig {


    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
