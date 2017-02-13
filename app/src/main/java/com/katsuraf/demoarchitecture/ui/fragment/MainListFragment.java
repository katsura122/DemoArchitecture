package com.katsuraf.demoarchitecture.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.katsuraf.demoarchitecture.R;
import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.presenter.MainListPresenter;
import com.katsuraf.demoarchitecture.ui.adapter.MainListAdapter;
import com.katsuraf.demoarchitecture.ui.view.IMainListView;
import com.katsuraf.demoarchitecture.utils.NetworkUtil;

import java.util.List;

import butterknife.BindView;

public class MainListFragment extends BaseFragment implements IMainListView,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.rl_retry)
    RelativeLayout mLayoutRetry;

    @BindView(R.id.bt_retry)
    Button mBtnRetry;

    @BindView(R.id.loading_view)
    View loadingView;

    private LoadMode loadMode;

    private MainListAdapter mAdapter;

    private MainListPresenter mPresenter;

    public enum LoadMode {
        FIRST_LOAD,
        REFRESH,
        LOAD_MORE,
    }

    public static Fragment getInstance() {
        return new MainListFragment();
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter = new MainListPresenter(getContext(), this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadMode = LoadMode.FIRST_LOAD;
        initAdapter();
        getListData();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_data_list;
    }

    private void initAdapter() {
        mAdapter = new MainListAdapter(R.layout.layout_main_list_cell);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view,
                                          final int position) {
                showToast(((ListItemEntity) adapter.getData().get(position)).getTitle());
            }
        });
    }

    private void getListData() {
        mPresenter.getListData(loadMode);
    }

    @Override
    public void showList(List<ListItemEntity> list) {
        switch (loadMode) {
            case REFRESH:
            case FIRST_LOAD:
                mAdapter.setNewData(list);
                mSwipeRefreshLayout.setRefreshing(false);
                mSwipeRefreshLayout.setEnabled(true);
                mAdapter.setEnableLoadMore(true);
                break;
            case LOAD_MORE:
                mAdapter.addData(list);
                mAdapter.loadMoreComplete();
                mSwipeRefreshLayout.setEnabled(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void showNoMore() {
        mAdapter.loadMoreEnd(true);
        mSwipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        loadMode = LoadMode.REFRESH;
        getListData();
    }

    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        loadMode = LoadMode.LOAD_MORE;
        if (!NetworkUtil.isNetworkConnectivity(getContext())) {
            showError(getString(R.string.exp_msg_no_connection));
        }
        getListData();
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        if (mAdapter.getData().size() == 0) {
            showRetry();
            mSwipeRefreshLayout.setEnabled(false);
        } else if (loadMode == LoadMode.LOAD_MORE) {
            mAdapter.loadMoreFail();
        }
        showSnackbar(mSwipeRefreshLayout, message);
    }

}
