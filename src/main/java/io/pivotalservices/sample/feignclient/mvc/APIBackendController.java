package io.pivotalservices.sample.feignclient.mvc;

import io.pivotalservices.sample.feignclient.model.APIInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This controller has methods to simulate two backend services serving at /api1 and /api2
 */
@Controller
public class APIBackendController {

    @RequestMapping("/api1/info/{data}")
    @ResponseBody
    public APIInfo infos(@PathVariable("data") String data) {
        return new APIInfo(1, data);
    }

    @RequestMapping("/api1/notfound")
    public void api1(HttpServletResponse resp) {
        try {
            resp.sendError(404, "not-found");
        } catch (IOException e) {
            /* don't do anything here */
        }
    }

    @RequestMapping("/api2/info/{data}")
    @ResponseBody
    public APIInfo api2(@PathVariable("data") String data) {
        return new APIInfo(2, data);
    }

    @RequestMapping("/api2/timeout")
    @ResponseBody
    public APIInfo api2() {
        try {
            // Sleep enough to trigger a timeout error
            Thread.sleep(1600);
        } catch (Exception e) {
            /* intentionally left empty */
        }
        return new APIInfo(2, "timeout");
    }
}
