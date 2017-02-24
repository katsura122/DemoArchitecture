package com.katsuraf.demoarchitecture.ui.view;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;

import java.util.List;

public interface IMainListView extends ILoadDataView {
    void showList(List<ListItemEntity> list);
    void showNoMore();
}
