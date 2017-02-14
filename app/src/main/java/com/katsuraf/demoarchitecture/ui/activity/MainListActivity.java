package com.katsuraf.demoarchitecture.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.katsuraf.demoarchitecture.R;
import com.katsuraf.demoarchitecture.ui.fragment.MainListFragment;

public class MainListActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainListActivity.class);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_layout;
    }

    @Override
    protected void initViewsAndEvents() {
        addFragment(R.id.fragment_container, MainListFragment.getInstance());
    }
}
