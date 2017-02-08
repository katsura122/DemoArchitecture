package com.katsuraf.demoarchitecture.db.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ListItemEntity {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("Id")
    private long itemId;

    @SerializedName("Type")
    private int type;

    @SerializedName("Title")
    private String title;

    @SerializedName("SubTitle")
    private String subTitle;

    @SerializedName("Link")
    private String link;

    @SerializedName("Image")
    private String imageLink;

    @SerializedName("Date")
    private String date;

    @SerializedName("ReadCount")
    private String readCount;

    @SerializedName("CommentCount")
    private String commentCount;

    @Generated(hash = 1782549936)
    public ListItemEntity(Long id, long itemId, int type, String title,
            String subTitle, String link, String imageLink, String date,
            String readCount, String commentCount) {
        this.id = id;
        this.itemId = itemId;
        this.type = type;
        this.title = title;
        this.subTitle = subTitle;
        this.link = link;
        this.imageLink = imageLink;
        this.date = date;
        this.readCount = readCount;
        this.commentCount = commentCount;
    }

    @Generated(hash = 1154079530)
    public ListItemEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageLink() {
        return this.imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReadCount() {
        return this.readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }


}
