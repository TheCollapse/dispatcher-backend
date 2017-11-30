package com.spbgti.dispatcherapp.Entity.Event;

public class SessionInfo {
    private String ip;
    private String other;

    public SessionInfo() {
    }

    public SessionInfo(String ip, String other) {
        this.ip = ip;
        this.other = other;
    }

    public String getIp() {
        return ip;
    }

    public String getOther() {
        return other;
    }
}
