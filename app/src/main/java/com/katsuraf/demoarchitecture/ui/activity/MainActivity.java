package com.katsuraf.demoarchitecture.ui.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jakewharton.rxbinding.view.RxView;
import com.katsuraf.demoarchitecture.R;
import com.katsuraf.demoarchitecture.presenter.SearchPresenter;
import com.katsuraf.demoarchitecture.ui.adapter.KeywordsAdapter;
import com.katsuraf.demoarchitecture.ui.view.IKeywordsView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IKeywordsView {

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.rv_search_keywords)
    RecyclerView rvKeywordsView;

    @BindView(R.id.btn_enter)
    Button mBtnEnter;

    private SearchPresenter mSearchPresenter;

    private KeywordsAdapter mKeywordsAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        initSearchView();
        RxView.clicks(mBtnEnter).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(s -> navigateTo(MainListActivity.class, null));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSwipeBackEnable(false);
    }

    private void initSearchView() {
        mSearchPresenter = new SearchPresenter(this);
        mSearchPresenter.setKeywordsView(this);
        mKeywordsAdapter = new KeywordsAdapter();
        rvKeywordsView.setLayoutManager(new LinearLayoutManager(this));
        rvKeywordsView.setAdapter(mKeywordsAdapter);
        rvKeywordsView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.img_delete_keywords:
                        mSearchPresenter.deleteKeywords(mKeywordsAdapter.getItem(i));
                        mKeywordsAdapter.removeKeywords(i);
                        break;
                    case R.id.txt_keywords:
                        mSearchPresenter.doSearch(mKeywordsAdapter.getItem(i), false);
                        searchView.setQuery(mKeywordsAdapter.getItem(i), false);
                        showKeywordsView(false);
                        hideSoftInputWindow(searchView);
                        break;
                    case R.id.txt_clear_all_keywords:
                        mSearchPresenter.deleteAllKeywords();
                        mKeywordsAdapter.removeAll();
                        break;
                    default:
                        break;
                }
            }
        });
        searchView.setQueryHint(getString(R.string.hint_search_keywords));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MainActivity.this.showKeywordsView(false);
                mSearchPresenter.addKeywords(query);
                mSearchPresenter.doSearch(query, false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText) && rvKeywordsView.getVisibility() == View.VISIBLE) {
                    MainActivity.this.showKeywordsView(true);
                }
                return false;
            }
        });
        searchView.setOnCloseListener(() -> {
            showSearchView(false);
            return false;
        });
        searchView.setOnSearchClickListener(v -> showSearchView(true));
    }

    private void showSearchView(boolean isShown) {

    }

    private void hideSoftInputWindow(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    public void showKeywords(List<String> keywords) {

    }

    @Override
    public void showKeywordsView(boolean isShow) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.FADE;
    }
}
