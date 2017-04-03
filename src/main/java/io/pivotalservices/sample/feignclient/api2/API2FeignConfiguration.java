package io.pivotalservices.sample.feignclient.api2;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Feign Configuration class related to API2.
 */
public class API2FeignConfiguration {

    @Value("${api2.connectionTimeout:1000}")
    int connectionTimeout;

    @Value("${api2.readTimeout:3000}")
    int readTimeout;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectionTimeout, readTimeout);
    }
}
