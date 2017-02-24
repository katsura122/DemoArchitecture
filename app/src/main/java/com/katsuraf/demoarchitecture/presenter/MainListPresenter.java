package com.katsuraf.demoarchitecture.presenter;

import android.content.Context;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.model.IMainListModel;
import com.katsuraf.demoarchitecture.model.imp.MainListModel;
import com.katsuraf.demoarchitecture.net.OnHttpCallBack;
import com.katsuraf.demoarchitecture.ui.fragment.MainListFragment;
import com.katsuraf.demoarchitecture.ui.view.IMainListView;

import java.util.List;

public class MainListPresenter implements Presenter {

    private IMainListView mMainListView;
    private IMainListModel mMainListModel;

    public MainListPresenter(Context context, IMainListView mainListView) {
        this.mMainListView = mainListView;
        mMainListModel = new MainListModel(context, new OnListCallBack());
    }

    public void getListData(MainListFragment.LoadMode loadMode) {
        mMainListModel.requestData(loadMode);
    }

    private class OnListCallBack implements OnHttpCallBack<List<ListItemEntity>> {

        @Override
        public void onSuccessful(List<ListItemEntity> result) {
            mMainListView.showList(result);
        }

        @Override
        public void onFailed(String errorMsg) {
            mMainListView.showError(errorMsg);
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
