package com.katsuraf.demoarchitecture.net;

import android.content.Context;
import android.os.Build;

import com.google.gson.annotations.SerializedName;
import com.katsuraf.demoarchitecture.BuildConfig;

import java.io.UnsupportedEncodingException;

import javax.inject.Singleton;

@Singleton
class UserAgent {

    @SerializedName("Model")
    private String deviceType = Build.MODEL;

    @SerializedName("Brand")
    private String brand = Build.BRAND;

    @SerializedName("OS")
    private String osName = "Android " + Build.VERSION.RELEASE;

    @SerializedName("OSDetail")
    private String osVersion = android.os.Build.VERSION.RELEASE;

    @SerializedName("Udid")
    private String udid;

    @SerializedName("Secret")
    private String secret;

    @SerializedName("Timestamp")
    private String time = Long.toString(System.currentTimeMillis());

    @SerializedName("Version")
    private String appVersion = BuildConfig.VERSION_NAME;

    @SerializedName("VersionCode")
    private String appVersionCode = BuildConfig.VERSION_CODE + "";

    private transient Context context;

    public UserAgent(Context context) {
        this.context = context;
    }

    public UserAgent build() {
        StringBuilder builder = new StringBuilder();
        time = Long.toString(System.currentTimeMillis());
        builder.append(time).append(appVersion).append(osVersion);
        secret = builder.toString();
        return this;
    }

}
