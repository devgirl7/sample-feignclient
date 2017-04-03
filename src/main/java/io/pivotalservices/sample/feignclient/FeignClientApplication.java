package io.pivotalservices.sample.feignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class FeignClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}
}