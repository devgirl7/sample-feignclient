package io.pivotalservices.sample.feignclient.exception;

public class FeignClientException extends RuntimeException {
    private int status;
    private boolean generic;

    public FeignClientException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.generic = false;
    }

    public FeignClientException(Throwable cause) {
        super(cause);
        this.generic = true;
    }

    public int getStatus() {
        return status;
    }

    public boolean isGeneric() {
        return generic;
    }
}
