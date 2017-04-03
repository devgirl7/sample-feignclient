package io.pivotalservices.sample.feignclient.model;

/**
 * Created by pparama on 3/28/17.
 */
public class ErrorInfo {

    private boolean timeout;
    private int httpStatus;

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
