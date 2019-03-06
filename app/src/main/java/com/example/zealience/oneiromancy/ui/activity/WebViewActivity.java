package com.example.zealience.oneiromancy.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zealience.oneiromancy.R;
import com.steven.base.app.BaseApp;
import com.steven.base.base.BaseActivity;

public class WebViewActivity extends BaseActivity {
    private WebView webView;
    private ProgressBar mProgressBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        BaseApp.getInstance().addActivity(this);
        showTitle("");
        setWhiteStatusBar(R.color.white);
        webView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        WebSettings settings = webView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        webView.requestFocus();
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage cm) {
                String log = "错误信息：" + cm.message() + "。发生错误的代码行数："
                        + cm.lineNumber() + "。资源id："
                        + cm.sourceId();
                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getTitlebar().setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    mProgressBar.setProgress(progress);
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setProgress(progress);
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl("https://www.zhihu.com/topic/19556496/hot");
    }
}