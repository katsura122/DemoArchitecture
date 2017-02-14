package com.katsuraf.demoarchitecture.request;

import com.google.gson.annotations.SerializedName;

public class MainListRequest extends PageRequest {

    @SerializedName("ListType")
    private Integer listType;

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

}
