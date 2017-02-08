package com.katsuraf.demoarchitecture.net;

/**
 * 公共的请求回调监听器
 * Created by HDL on 2016/7/29.
 */
public interface OnHttpCallBack<T> {
    void onSuccessful(T t);

    void onFailed(String errorMsg);
}
