package com.katsuraf.demoarchitecture.response;

import com.google.gson.annotations.SerializedName;

public class ResultResponse {
    @SerializedName("Code")
    private int code; //result code

    @SerializedName("ResultMsg")
    private String resultMsg; //result message;

    public int getCode() {
        return code;
    }

    public String getResultMsg() {
        return resultMsg;
    }
}
