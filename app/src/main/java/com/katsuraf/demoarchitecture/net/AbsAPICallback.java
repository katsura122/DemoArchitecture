package com.katsuraf.demoarchitecture.net;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.katsuraf.demoarchitecture.net.exception.ApiException;
import com.katsuraf.demoarchitecture.net.exception.ResultException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;

public abstract class AbsAPICallback<T> extends DisposableObserver<T> {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    //出错提示
    private final String networkMsg;
    private final String parseMsg;
    private final String unknownMsg;

    protected AbsAPICallback(String networkMsg, String parseMsg, String unknownMsg) {
        this.networkMsg = networkMsg;
        this.parseMsg = parseMsg;
        this.unknownMsg = unknownMsg;
    }

    @Override
    public void onError(Throwable e) {
        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }

        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code(), httpException.message());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    onPermissionError(ex);          //权限错误，需要实现
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage(networkMsg);  //均视为网络错误
                    onError(ex);
                    break;
            }
        } else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, REQUEST_TIMEOUT, networkMsg);
            onResultError(ex);
        } else if (e instanceof ResultException) {    //服务器返回的错误
            ResultException resultException = (ResultException) e;
            ex = new ApiException(resultException, resultException.getErrCode(),
                    resultException.getErrMsg());
            onResultError(ex);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, ApiException.PARSE_ERROR, parseMsg);
            onError(ex);
        } else if (e instanceof UnknownHostException) {
            e.printStackTrace();
            ex = new ApiException(e, ApiException.UNKNOWN_HOST, networkMsg);
            onError(ex);
        } else {
            e.printStackTrace();
            ex = new ApiException(e, ApiException.UNKNOWN, unknownMsg);
            onError(ex);
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);

    /**
     * 权限错误，需要实现重新登录操作
     */
    protected abstract void onPermissionError(ApiException ex);

    /**
     * 获取数据错误
     */
    protected abstract void onResultError(ApiException ex);

}