package com.katsuraf.demoarchitecture.ui.activity;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.katsuraf.library.base.BaseSwipeBackCompatActivity;
import com.katsuraf.demoarchitecture.R;

import butterknife.ButterKnife;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends BaseSwipeBackCompatActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mToolbar = ButterKnife.findById(this, R.id.toolbar_main);
        if (isAddToolBar() && mToolbar != null) {
            setSupportActionBar(mToolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            if (isNavigationButtonEnable()) {
                RxToolbar.navigationClicks(mToolbar).subscribe(this::onToolbarNavigationClicked);
            }
            if (getToolbarTitleRes() > 0) {
                mToolbar.setTitle(getToolbarTitleRes());
            }
        }
    }

    protected void onToolbarNavigationClicked(Void v) {
        onBackPressed();
    }

    protected @StringRes
    int getToolbarTitleRes() {
        return 0;
    }

    protected boolean isAddToolBar() {
        return true;
    }

    protected boolean isNavigationButtonEnable() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }
}
