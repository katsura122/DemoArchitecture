package com.katsuraf.demoarchitecture.ui.activity;

import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.katsuraf.demoarchitecture.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_enter)
    Button mBtnEnter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        RxView.clicks(mBtnEnter).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(s -> navigateTo(MainListActivity.class, null));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSwipeBackEnable(false);
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.FADE;
    }
}
