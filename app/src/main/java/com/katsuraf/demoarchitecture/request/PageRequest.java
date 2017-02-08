package com.katsuraf.demoarchitecture.request;

import com.google.gson.annotations.SerializedName;

public class PageRequest {
    @SerializedName("Timestamp")
    private Long timestamp = 0L;

    @SerializedName("PageNum")
    private Integer page = 1;

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
}
