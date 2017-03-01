package com.katsuraf.demoarchitecture.presenter;

import android.content.Context;

import com.katsuraf.demoarchitecture.model.IMainListModel;
import com.katsuraf.demoarchitecture.model.imp.MainListModel;
import com.katsuraf.demoarchitecture.net.DefaultSubscriber;
import com.katsuraf.demoarchitecture.net.exception.ApiException;
import com.katsuraf.demoarchitecture.response.ListResponse;
import com.katsuraf.demoarchitecture.ui.fragment.MainListFragment;
import com.katsuraf.demoarchitecture.ui.view.IMainListView;

public class MainListPresenter implements Presenter {

    private IMainListView mMainListView;
    private IMainListModel mMainListModel;

    public MainListPresenter(Context context, IMainListView mainListView) {
        this.mMainListView = mainListView;
        mMainListModel = new MainListModel(context, new MainListSubscriber(context));
    }

    public void getListData(MainListFragment.LoadMode loadMode) {
        mMainListModel.requestData(loadMode);
    }

    private final class MainListSubscriber extends DefaultSubscriber<ListResponse> {

        MainListSubscriber(Context context) {
            super(context);
        }

        @Override
        protected void onError(ApiException ex) {
            mMainListView.showError(ex.getDisplayMessage());
        }

        @Override
        protected void onPermissionError(ApiException ex) {
            mMainListView.showError(ex.getDisplayMessage());
        }

        @Override
        protected void onResultError(ApiException ex) {
            mMainListView.showError(ex.getDisplayMessage());
        }

        @Override
        public void onNext(ListResponse response) {
            mMainListView.showList(response.getList());
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
