package io.pivotalservices.sample.feignclient.mvc;

import io.pivotalservices.sample.feignclient.model.APIInfo;
import io.pivotalservices.sample.feignclient.service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIClientWithFallbackController {
	private static final Logger LOGGER = LoggerFactory.getLogger(APIClientWithFallbackController.class);

	@Autowired
	APIService apiService;

	@RequestMapping(value = "/client/timeout/fallback")
	public APIInfo hystrixTimeoutFallback() {
		return apiService.timeout();
	}

	@RequestMapping(value = "/client/notfound/fallback")
	public APIInfo hystrixNotfoundFallback() {
		return apiService.notfound();
	}
}
