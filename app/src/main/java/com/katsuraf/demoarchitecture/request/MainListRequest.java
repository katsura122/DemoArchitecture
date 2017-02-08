package com.katsuraf.demoarchitecture.request;

import com.google.gson.annotations.SerializedName;

public class MainListRequest extends PageRequest {

    @SerializedName("ListType")
    private int listType;

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

}
