package io.pivotalservices.sample.feignclient.api1;

import io.pivotalservices.sample.feignclient.model.APIInfo;
import io.pivotalservices.sample.feignclient.exception.FeignClientException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api1", url="${api1.uri}")
public interface API1FeignClient {

    @RequestMapping(value="/info/{data}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    APIInfo apiInfo(@PathVariable("data") String data) throws FeignClientException;


    @RequestMapping(value="/notfound")
    void apiNotFound() throws FeignClientException;
}
