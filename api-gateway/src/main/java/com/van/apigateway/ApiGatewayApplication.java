package com.van.apigateway;

import com.van.apigateway.filter.IpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    /**
     * 在实现了自定义过滤器后，他不会直接生效，还需要为其创建具体的Bean才能启动该过滤器
     * @return
     */
    @Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }
}
