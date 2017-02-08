package com.katsuraf.demoarchitecture.ui.view;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;

import java.util.List;

/**
 * 类描述：
 * 创建人：user
 * 创建时间：2017/2/3 11:47
 * 修改人：user
 * 修改时间：2017/2/3 11:47
 * 修改备注：
 *
 * @Copyright (C) 2017 Chengdu Economic Daily NNL Inc. All rights reserved.
 */

public interface IMainListView extends LoadDataView {
    void showList(List<ListItemEntity> list);
    void showNoMore();
}
