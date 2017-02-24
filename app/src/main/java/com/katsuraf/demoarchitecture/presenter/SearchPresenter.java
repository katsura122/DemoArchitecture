package com.katsuraf.demoarchitecture.presenter;

import android.content.Context;

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.model.ISearchModel;
import com.katsuraf.demoarchitecture.model.imp.SearchModel;
import com.katsuraf.demoarchitecture.net.OnHttpCallBack;
import com.katsuraf.demoarchitecture.ui.view.IKeywordsView;
import com.katsuraf.demoarchitecture.ui.view.ISearchResultView;

import java.util.List;

public class SearchPresenter implements Presenter {
    private IKeywordsView mKeywordsView;
    private ISearchResultView mSearchResultView;
    private ISearchModel mSearchModel;
    private Context context;
    private String searchKeyword;
    private boolean isSearchMore;

    public SearchPresenter(Context context) {
        this.context = context;
        mSearchModel = new SearchModel();
    }

    public void setKeywordsView(IKeywordsView keywordsView) {
        this.mKeywordsView = keywordsView;
    }

    public void setSearchResultView(ISearchResultView searchResultView) {
        this.mSearchResultView = searchResultView;
    }

    public void getKeywordsList() {
        mSearchModel.getKeywords();
    }

    public void addKeywords(String keywords) {
        mSearchModel.saveKeywords(keywords);
    }

    public void deleteKeywords(String keywords) {
        mSearchModel.deleteKeywords(keywords);
    }

    public void deleteAllKeywords() {
        mSearchModel.deleteAllKeywords();
    }

    public void doSearch(String keywords, boolean isLoadMore) {
        this.searchKeyword = keywords;
        this.isSearchMore = false;
        mSearchModel.querySearch(keywords, isLoadMore);
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

    private class OnGetKeywordsCallBack implements OnHttpCallBack<List<String>> {

        @Override
        public void onSuccessful(List<String> result) {
            mKeywordsView.showKeywords(result);
        }

        @Override
        public void onFailed(String errorMsg) {
            mKeywordsView.showError(errorMsg);
        }
    }

    private class OnQuerySearchCallBack implements OnHttpCallBack<List<ListItemEntity>> {

        @Override
        public void onSuccessful(List<ListItemEntity> result) {
            mSearchResultView.showSearchResult(result, isSearchMore);
        }

        @Override
        public void onFailed(String errorMsg) {
            mKeywordsView.showError(errorMsg);
        }
    }

}
