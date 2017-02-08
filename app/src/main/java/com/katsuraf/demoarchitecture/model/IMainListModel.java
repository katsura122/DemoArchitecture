package com.katsuraf.demoarchitecture.model;

import com.katsuraf.demoarchitecture.ui.fragment.MainListFragment;

public interface IMainListModel {
    void requestData(MainListFragment.LoadMode loadMode);
}
