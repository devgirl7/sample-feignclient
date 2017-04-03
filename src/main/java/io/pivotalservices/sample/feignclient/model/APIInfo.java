package io.pivotalservices.sample.feignclient.model;

public class APIInfo {

    private int version;
    private String info;

    public APIInfo() { }

    public APIInfo(int version, String info) {
        this.version = version;
        this.info = info;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
