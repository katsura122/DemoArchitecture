package com.katsuraf.demoarchitecture.net;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.katsuraf.demoarchitecture.BuildConfig;
import com.katsuraf.demoarchitecture.constants.NetConstant;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpClientProvider {
    private Context context;

    public OkHttpClientProvider(Context context) {
        this.context = context;
    }

    @Singleton
    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(NetConstant.DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        builder.readTimeout(NetConstant.DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        builder.writeTimeout(NetConstant.DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            builder.addNetworkInterceptor(new StethoInterceptor());
            builder.retryOnConnectionFailure(true);
        }
        builder.addInterceptor(new UserAgentInterceptor(new UserAgent(context)));
        setHttpResponseCache(builder, context.getApplicationContext());
        return builder.build();
    }

    private void setHttpResponseCache(OkHttpClient.Builder builder, Context context) {
        File baseDir = context.getCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, "HttpResponseCache");
            builder.cache(new Cache(cacheDir, NetConstant.HTTP_RESPONSE_DISK_CACHE_MAX_SIZE));
        }
    }
}
