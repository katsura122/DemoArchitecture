package com.katsuraf.demoarchitecture.net.exception;

public class ApiException extends Exception {

    private final int code;
    private String displayMessage;

    public static final int UNKNOWN = 1000;
    public static final int PARSE_ERROR = 1001;
    public static final int UNKNOWN_HOST = 1002;

    public ApiException(Throwable throwable, int code, String msg) {
        super(throwable);
        this.code = code;
        this.displayMessage =  msg + "(code:" + code + ")";
    }

    public int getCode() {
        return code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String msg) {
        this.displayMessage = msg + "(code:" + code + ")";
    }
}