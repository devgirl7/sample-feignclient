package io.pivotalservices.sample.feignclient.mvc;

import io.pivotalservices.sample.feignclient.exception.FeignClientException;
import io.pivotalservices.sample.feignclient.api1.API1FeignClient;
import io.pivotalservices.sample.feignclient.api2.API2FeignClient;
import io.pivotalservices.sample.feignclient.model.APIInfo;
import io.pivotalservices.sample.feignclient.model.ErrorInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIClientController {
	private static final Logger LOGGER = LoggerFactory.getLogger(APIClientController.class);

	@Autowired
	API1FeignClient api1FeignClient;

	@Autowired
    API2FeignClient api2FeignClient;

	@RequestMapping(value = "/client/infos")
	@ResponseBody
	public List<APIInfo> infos() {
		return Lists.newArrayList(api1FeignClient.apiInfo("api-call1"), api2FeignClient.apiInfo("api-call2"));
	}

	@RequestMapping(value = "/client/timeout")
	public ErrorInfo timeout() {
		try {
			api2FeignClient.apiTimeout();
		} catch (FeignClientException fce) {
			ErrorInfo info = new ErrorInfo();
			info.setTimeout(fce.isGeneric());
			info.setHttpStatus(fce.getStatus());
			return info;
		}
		return null;
	}

	@RequestMapping(value = "/client/notfound")
	public ErrorInfo notfound() {
		try {
			api1FeignClient.apiNotFound();
		} catch (FeignClientException fce) {
			ErrorInfo info = new ErrorInfo();
			info.setHttpStatus(fce.getStatus());
			return info;
		}
		return null;
	}


}
