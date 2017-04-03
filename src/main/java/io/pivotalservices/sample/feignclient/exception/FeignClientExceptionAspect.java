package io.pivotalservices.sample.feignclient.exception;

import feign.FeignException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

/**
 * Spring AOP throwing advice which captures the feign specific exception thrown from all the feign clients
 * and return a application specific exception with the original status and reason for failure
 * It captures timeout as well as other API errors (such as 4xx, 5xx)
 */

@Aspect
@Component
public class FeignClientExceptionAspect {

    @AfterThrowing(pointcut = "execution(* io.pivotalservices.sample.feignclient.api*.*FeignClient.*(..))", throwing = "re")
    public void afterThrowingAdvice(RuntimeException re) throws FeignClientException {
        if (re instanceof FeignException && ((FeignException) re).status() > 0) {
            throw new FeignClientException(((FeignException) re).status(), re.getMessage(), re);
        } else {
            throw new FeignClientException(re);
        }
    }
}
