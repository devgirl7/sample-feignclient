package io.pivotalservices.sample.feignclient;

import io.pivotalservices.sample.feignclient.model.APIInfo;
import io.pivotalservices.sample.feignclient.model.ErrorInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		properties = {"server.port=45678", "api1.uri=http://localhost:45678/api1", "api2.uri=http://localhost:45678/api2"})
public class FeignClientApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testInfos() {
		ResponseEntity<List<APIInfo>> infos = restTemplate.exchange("/client/infos", HttpMethod.GET,
											null, new ParameterizedTypeReference<List<APIInfo>>() { });
		Assert.assertEquals("Infos size should be 2", 2, infos.getBody().size());
	}

	@Test
	public void testTimeout() {
		ResponseEntity<ErrorInfo> info = restTemplate.exchange("/client/timeout", HttpMethod.GET,
				null, ErrorInfo.class);
		Assert.assertNotNull("error-info should be present", info.getBody());
		Assert.assertTrue("error-info timeout should be true", info.getBody().isTimeout());
	}

	@Test
	public void testTimeoutFallback() {
		ResponseEntity<APIInfo> info = restTemplate.exchange("/client/timeout/fallback", HttpMethod.GET,
				null, APIInfo.class);
		Assert.assertNotNull("info should be present", info.getBody());
		Assert.assertEquals("info version should be 202", 202, info.getBody().getVersion());
		Assert.assertEquals("info message is not correct", "timeout-fallback", info.getBody().getInfo());
	}

	@Test
	public void testNotFound() {
		ResponseEntity<ErrorInfo> info = restTemplate.exchange("/client/notfound", HttpMethod.GET,
				null, ErrorInfo.class);
		Assert.assertNotNull("error-info should be present", info.getBody());
		Assert.assertFalse("error-info timeout should be false", info.getBody().isTimeout());
		Assert.assertEquals("error-info status should be 404", 404, info.getBody().getHttpStatus());
	}

	@Test
	public void testNotfoundFallback() {
		ResponseEntity<APIInfo> info = restTemplate.exchange("/client/notfound/fallback", HttpMethod.GET,
				null, APIInfo.class);
		Assert.assertNotNull("info should be present", info.getBody());
		Assert.assertEquals("info version should be 202", 203, info.getBody().getVersion());
		Assert.assertEquals("info message is not correct", "notfound-fallback", info.getBody().getInfo());
	}

}
