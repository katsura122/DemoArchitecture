package com.katsuraf.demoarchitecture.model.imp;

import com.katsuraf.demoarchitecture.net.OnHttpCallBack;
import com.katsuraf.demoarchitecture.request.PageRequest;

abstract public class BasePageModel{

    protected OnHttpCallBack onHttpCallBack;
    protected PageRequest pageRequest;
    protected boolean isRefresh;

    public BasePageModel(OnHttpCallBack onHttpCallBack) {
        this.onHttpCallBack = onHttpCallBack;
        pageRequest = getRequest();
    }

    abstract protected PageRequest getRequest();

    protected void sendRequest(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageRequest.setPage(1);
            pageRequest.setTimestamp(0L);
        }
    }
}
