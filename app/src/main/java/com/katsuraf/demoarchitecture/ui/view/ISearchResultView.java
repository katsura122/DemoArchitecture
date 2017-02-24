package com.katsuraf.demoarchitecture.ui.view;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;

import java.util.List;

public interface ISearchResultView extends ILoadDataView{
    void showSearchResult(List<ListItemEntity> dataList, boolean isSearchMore);
    void showNoMoreSearchData();
    void showNoSearchData();
}
