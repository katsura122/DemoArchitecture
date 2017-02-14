package com.katsuraf.demoarchitecture.request;

import com.google.gson.annotations.SerializedName;

public class PageRequest {
    @SerializedName("TimeStamp")
    private Long timestamp;

    @SerializedName("Page")
    private Integer page;

    @SerializedName("ViewTimes")
    private int viewTimes;

    public PageRequest() {
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }
}
