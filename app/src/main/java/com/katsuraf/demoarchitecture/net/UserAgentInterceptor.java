package com.katsuraf.demoarchitecture.net;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class UserAgentInterceptor implements Interceptor {
    private static final String USER_AGENT_HEADER_NAME = "DeviceInfo";
    private final UserAgent userAgent;
    private final Gson gson;


    public UserAgentInterceptor(UserAgent userAgent) {
        this.userAgent = userAgent;
        this.gson = new Gson();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String userHeader = gson.toJson(userAgent.build(), UserAgent.class);
        final Request originalRequest = chain.request();
        final Request requestWithUserAgent = originalRequest.newBuilder()
                .addHeader(USER_AGENT_HEADER_NAME, userHeader)
                .build();
        Log.d("UserAgentInterceptor", userHeader);
        return chain.proceed(requestWithUserAgent);
    }
}