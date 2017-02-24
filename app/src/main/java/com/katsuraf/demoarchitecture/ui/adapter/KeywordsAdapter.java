package com.katsuraf.demoarchitecture.ui.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.katsuraf.demoarchitecture.R;

import java.util.List;

public class KeywordsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public static final int CLEAR_VIEW_TYPE = 111;

    public KeywordsAdapter() {
        super(R.layout.lay_keywords_cell, null);

    }

    @Override
    protected void convert(BaseViewHolder viewHolder, String s) {
        viewHolder.setText(R.id.txt_keywords, s);
        viewHolder.addOnClickListener(R.id.img_delete_keywords);
        viewHolder.addOnClickListener(R.id.txt_keywords);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    public void setKeywordsList(List<String> keywordsList) {
        getData().clear();
        getData().addAll(keywordsList);
        notifyDataSetChanged();
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CLEAR_VIEW_TYPE) {
            return createBaseViewHolder(parent, R.layout.lay_clear_all_keywords);
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected int getDefItemViewType(int position) {
        if (position == mData.size() - 1) {
            return CLEAR_VIEW_TYPE;
        }
        return super.getDefItemViewType(position);
    }

    public void removeAll() {
        getData().clear();
        notifyDataSetChanged();
    }

    public void removeKeywords(int position) {
        getData().remove(position);
        if (getData().size() == 1) {
            getData().remove(0);
        }
        notifyDataSetChanged();
    }
}
