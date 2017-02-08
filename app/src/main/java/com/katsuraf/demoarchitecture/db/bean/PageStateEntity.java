package com.katsuraf.demoarchitecture.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class PageStateEntity {
    @Id(autoincrement = true)
    private Long id;

    @Unique
    private int listType;

    private Long timestamp;

    private Integer page;

    public PageStateEntity(Integer listType) {
        this.listType = listType;
    }

    @Generated(hash = 1029194835)
    public PageStateEntity(Long id, int listType, Long timestamp, Integer page) {
        this.id = id;
        this.listType = listType;
        this.timestamp = timestamp;
        this.page = page;
    }

    @Generated(hash = 881185569)
    public PageStateEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getListType() {
        return this.listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }


}
