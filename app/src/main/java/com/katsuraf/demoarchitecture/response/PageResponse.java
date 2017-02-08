package com.katsuraf.demoarchitecture.response;

import com.google.gson.annotations.SerializedName;

public class PageResponse {
    @SerializedName("Timestamp")
    protected Long timestamp;

    @SerializedName("PageNum")
    protected Integer pageNo;

    @SerializedName("TotalPageNum")
    protected Integer totalPage;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
