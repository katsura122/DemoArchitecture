package com.katsuraf.demoarchitecture.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.katsuraf.demoarchitecture.R;
import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;

public class MainListAdapter extends BaseQuickAdapter<ListItemEntity, BaseViewHolder> {

    public MainListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListItemEntity listItem) {
        helper.setText(R.id.txt_title, listItem.getTitle());
        Glide.with(mContext).load(listItem.getImageLink())
                .crossFade()
                .placeholder(R.drawable.ic_image_default)
                .into((ImageView) helper.getView(R.id.img_news));
    }
}
