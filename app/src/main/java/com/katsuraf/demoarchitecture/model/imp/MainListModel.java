package com.katsuraf.demoarchitecture.model.imp;

import android.content.Context;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.db.bean.PageStateEntity;
import com.katsuraf.demoarchitecture.db.cache.ListCache;
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
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainListModel implements IMainListModel {

    private Context mContext;
    private MainListRequest mMainListRequest = new MainListRequest();
    private OnHttpCallBack<List<ListItemEntity>> mCallBack;
    private final ListCache mListCache;

    public MainListModel(Context context, ListCache listCache, OnHttpCallBack<List<ListItemEntity>> callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
        this.mListCache = listCache;
    }

    @Override
    public void requestData(MainListFragment.LoadMode loadMode) {
        switch (loadMode) {
            case FIRST_LOAD:
            case REFRESH:
                mMainListRequest.setPage(1);
                mMainListRequest.setTimestamp(0L);
                break;
            case LOAD_MORE:
                PageStateEntity pageState = mListCache.getPageState(0);
                mMainListRequest.setPage(pageState.getPage() + 1);
                mMainListRequest.setTimestamp(pageState.getTimestamp());
                break;
            default:
                break;
        }
        RestClient.newInstance()
                .create(ApiService.class)
                .getListData(mMainListRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(saveToCache)
                .subscribe(new MainListSubscriber(mContext));
    }

    private final Consumer<ListResponse> saveToCache = listResponse -> {
        if (listResponse != null) {
            if (mMainListRequest.getPage() == 1) {
                MainListModel.this.mListCache.clearList(mMainListRequest.getListType());
            }
            MainListModel.this.mListCache.putListData(listResponse.getList(), mMainListRequest.getListType());
            MainListModel.this.mListCache.putPageState(mMainListRequest.getListType(),
                    mMainListRequest.getPage(), mMainListRequest.getTimestamp());
        }
    };

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
