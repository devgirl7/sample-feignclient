package io.pivotalservices.sample.feignclient.service;

import io.pivotalservices.sample.feignclient.api1.API1FeignClient;
import io.pivotalservices.sample.feignclient.api2.API2FeignClient;
import io.pivotalservices.sample.feignclient.model.APIInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class APIService {

    @Autowired
    API1FeignClient api1FeignClient;

    @Autowired
    API2FeignClient api2FeignClient;

    @HystrixCommand(fallbackMethod = "timeoutFallback")
    public APIInfo timeout() {
        return api2FeignClient.apiTimeout();
    }

    public APIInfo timeoutFallback() {
        return new APIInfo(202, "timeout-fallback");
    }

    @HystrixCommand(fallbackMethod = "notfoundFallback")
    public APIInfo notfound() {
        api1FeignClient.apiNotFound();
        return new APIInfo(203, "this-should-never-happen");
    }

    public APIInfo notfoundFallback() {
        return new APIInfo(203, "notfound-fallback");
    }
}
