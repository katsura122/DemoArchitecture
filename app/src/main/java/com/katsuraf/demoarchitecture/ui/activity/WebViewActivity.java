package com.katsuraf.demoarchitecture.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.katsuraf.demoarchitecture.R;
import com.katsuraf.demoarchitecture.constants.Constant;
import com.katsuraf.demoarchitecture.ui.view.ILoadDataView;
import com.katsuraf.demoarchitecture.ui.widget.VideoEnabledWebChromeClient;
import com.katsuraf.demoarchitecture.ui.widget.VideoEnabledWebView;
import com.katsuraf.demoarchitecture.utils.NetworkUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity implements ILoadDataView {
    private final static String TAG = WebViewActivity.class.getSimpleName();

    @BindView(R.id.web_view)
    VideoEnabledWebView mWebView;

    @BindView(R.id.lay_non_video)
    LinearLayout mNonVideoLayout;

    @BindView(R.id.progress_web)
    ProgressBar mProgressBar;

    @BindView(R.id.rl_retry)
    RelativeLayout mLayoutRetry;

    @BindView(R.id.bt_retry)
    Button mBtnRetry;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViewsAndEvents() {
        initWebView(getIntent().getStringExtra(Constant.URI));
        RxView.clicks(mBtnRetry).throttleFirst(1, TimeUnit.SECONDS).subscribe(subs -> {
            if (!NetworkUtil.isNetworkConnectivity(this)) {
                showToast(getString(R.string.common_no_network_msg));
            } else {
                hideRetry();
                mWebView.reload();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {
        mLayoutRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        mLayoutRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @SuppressLint("SetJavaScriptEnabled, NewApi")
    protected void initWebView(String uri) {
        if (TextUtils.isEmpty(uri)) {
            Log.e(TAG, "the url is null");
        } else {
            uri = Uri.decode(uri).replaceAll(" ", "%20");
        }

        mWebView.setWebViewClient(new CustomWebViewClient());
        ViewGroup videoViewGroup = ButterKnife.findById(this, R.id.lay_video);
        View viewLoading = LayoutInflater.from(this).inflate(R.layout.view_progress, null);
        VideoEnabledWebChromeClient chromeClient = new CustomWebChromeClient(
                mNonVideoLayout, videoViewGroup, viewLoading, mWebView);
        chromeClient.setOnToggledFullscreen((boolean fullscreen) -> {
            // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
            if (fullscreen) {
                WindowManager.LayoutParams attrs = getWindow().getAttributes();
                attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                getWindow().setAttributes(attrs);
                if (Build.VERSION.SDK_INT >= 14) {
                    if (getWindow().getDecorView() != null) {
                        getWindow().getDecorView()
                                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                }
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                WindowManager.LayoutParams attrs = getWindow().getAttributes();
                attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                getWindow().setAttributes(attrs);
                if (Build.VERSION.SDK_INT >= 14) {
                    if (getWindow().getDecorView() != null) {
                        getWindow().getDecorView()
                                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }
        });
        mWebView.setWebChromeClient(chromeClient);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        settings.setAppCachePath(appCachePath);
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT > 11) {
            settings.setDisplayZoomControls(false);
        }
        if (NetworkUtil.isNetworkConnectivity(this)) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        loadWebContent(uri);
    }

    public void loadWebContent(String url) {
        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "load url is null");
            return;
        }
        if (!NetworkUtil.isNetworkConnectivity(this)) {
            showRetry();
            showToast(getString(R.string.common_no_network_msg));
            return;
        }
        mWebView.loadUrl(url);
    }

    class CustomWebChromeClient extends VideoEnabledWebChromeClient {

        CustomWebChromeClient(View activityNonVideoView,
                              ViewGroup activityVideoView, View loadingView,
                              VideoEnabledWebView webView) {
            super(activityNonVideoView, activityVideoView, loadingView, webView);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.INVISIBLE);
            } else {
                if (View.INVISIBLE == mProgressBar.getVisibility()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d(TAG, "onPageStarted url = " + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d(TAG, "onPageFinished url = " + url);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d(TAG, "call onReceivedError, errorCode: " + errorCode);
            switch (errorCode) {
                //ERROR_HOST_LOOKUP
                case ERROR_HOST_LOOKUP:
                case ERROR_TIMEOUT:
                    showRetry();
                    break;
                default:
                    showToast(getString(R.string.no_content_page_find));
                    break;
            }
        }

        @TargetApi(android.os.Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            switch (error.getErrorCode()) {
                //ERROR_HOST_LOOKUP
                case ERROR_HOST_LOOKUP:
                case ERROR_TIMEOUT:
                    showRetry();
                    break;
                default:
                    showToast(getString(R.string.no_content_page_find));
                    break;
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
