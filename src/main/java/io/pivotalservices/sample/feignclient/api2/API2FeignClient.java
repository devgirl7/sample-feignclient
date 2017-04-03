package io.pivotalservices.sample.feignclient.api2;

import io.pivotalservices.sample.feignclient.model.APIInfo;
import io.pivotalservices.sample.feignclient.exception.FeignClientException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api2", url = "${api2.uri}", configuration = {API2FeignConfiguration.class})
public interface API2FeignClient {

    @RequestMapping(value = "/info/{data}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    APIInfo apiInfo(@PathVariable("data") String data) throws FeignClientException;


    @RequestMapping(value = "/timeout", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    APIInfo apiTimeout() throws FeignClientException;
}
