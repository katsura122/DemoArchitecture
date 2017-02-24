package com.katsuraf.demoarchitecture.ui.view;

import java.util.List;

public interface IKeywordsView extends ILoadDataView {
    void showKeywords(List<String> keywords);
    void showKeywordsView(boolean isShow);
}
