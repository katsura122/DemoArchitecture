package com.katsuraf.demoarchitecture.ui.activity;

import com.katsuraf.demoarchitecture.R;
import com.katsuraf.demoarchitecture.ui.fragment.MainListFragment;

public class MainListActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_layout;
    }

    @Override
    protected void initViewsAndEvents() {
        addFragment(R.id.fragment_container, MainListFragment.getInstance());
    }
}
