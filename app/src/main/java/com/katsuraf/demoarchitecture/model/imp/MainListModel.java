package com.katsuraf.demoarchitecture.model.imp;

import android.content.Context;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.model.IMainListModel;
import com.katsuraf.demoarchitecture.net.ApiService;
import com.katsuraf.demoarchitecture.net.DefaultSubscriber;
import com.katsuraf.demoarchitecture.net.OnHttpCallBack;
import com.katsuraf.demoarchitecture.net.RestClient;
import com.katsuraf.demoarchitecture.net.exception.ApiException;
import com.katsuraf.demoarchitecture.request.MainListRequest;
import com.katsuraf.demoarchitecture.response.ListResponse;
import com.katsuraf.demoarchitecture.ui.fragment.MainListFragment;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainListModel implements IMainListModel {

    private Context mContext;
    private MainListRequest mMainListRequest;
    private OnHttpCallBack<List<ListItemEntity>> mCallBack;

    public MainListModel(Context context, OnHttpCallBack<List<ListItemEntity>> callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @Override
    public void requestData(MainListFragment.LoadMode loadMode) {
        MainListRequest listRequest = new MainListRequest();
        if (loadMode != MainListFragment.LoadMode.LOAD_MORE) {
            listRequest.setPage(1);
            listRequest.setTimestamp(0L);
        }
        RestClient.newInstance()
                .create(ApiService.class)
                .getListData(listRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainListSubscriber(mContext));
    }

    private final class MainListSubscriber extends DefaultSubscriber<ListResponse> {

        MainListSubscriber(Context context) {
            super(context);
        }

        @Override
        protected void onError(ApiException ex) {
        }

        @Override
        protected void onPermissionError(ApiException ex) {
        }

        @Override
        protected void onResultError(ApiException ex) {
            mCallBack.onFailed(ex.getDisplayMessage());
        }

        @Override
        public void onNext(ListResponse response) {
            mCallBack.onSuccessful(response.getList());
        }

        @Override
        public void onComplete() {

        }
    }
}
