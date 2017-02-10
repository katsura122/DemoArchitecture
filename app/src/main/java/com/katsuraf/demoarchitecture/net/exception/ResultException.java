package com.katsuraf.demoarchitecture.net.exception;

public class ResultException extends RuntimeException {
    public static final int NO_DATA = 2002;

    public static final String MSG_NO_DATA_FOUND = "no data";

    private int errCode = 0;
    private String msg;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return msg;
    }
}