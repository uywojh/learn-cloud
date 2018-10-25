package com.learning.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * feign启动服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SericeFeign {

    public static void main(String[] args) {
        SpringApplication.run(SericeFeign.class, args);
    }

}
