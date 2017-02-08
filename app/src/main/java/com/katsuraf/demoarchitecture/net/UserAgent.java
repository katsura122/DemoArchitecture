package com.katsuraf.demoarchitecture.net;

import android.content.Context;
import android.os.Build;

import com.google.gson.annotations.SerializedName;
import com.katsuraf.demoarchitecture.BuildConfig;

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

    private Context mContext;

    private volatile static UserAgent sInstance = null;

    private UserAgent(Context context) {
        this.mContext = context;
    }

    public static UserAgent getInstance(Context context) {
        if (sInstance == null) {
            synchronized (UserAgent.class) {
                if (sInstance == null) {
                    sInstance = new UserAgent(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public UserAgent build() {
        StringBuilder builder = new StringBuilder();
        time = Long.toString(System.currentTimeMillis());
        builder.append(time).append(appVersion).append(osVersion);
        secret = builder.toString();
        return this;
    }

}
