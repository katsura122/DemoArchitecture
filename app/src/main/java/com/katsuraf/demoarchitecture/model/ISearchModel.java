package com.katsuraf.demoarchitecture.model;

public interface ISearchModel {
    void saveKeywords(String keywords);
    void deleteKeywords(String keywords);
    void deleteAllKeywords();
    void getKeywords();
    void querySearch(String keywords, boolean isLoadMore);
}
