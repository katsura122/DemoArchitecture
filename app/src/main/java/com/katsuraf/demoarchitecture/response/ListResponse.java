package com.katsuraf.demoarchitecture.response;

import com.google.gson.annotations.SerializedName;
import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;

import java.util.List;


public class ListResponse extends PageResponse {
    @SerializedName("List")
    private List<ListItemEntity> list;

    public List<ListItemEntity> getList() {
        return list;
    }

    public void setList(List<ListItemEntity> list) {
        this.list = list;
    }
}
