package d.dao.easylife.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import d.dao.easylife.R;
import d.dao.easylife.constants.BaseUrl;

public class WebViewActivity extends BaseToolbarActivity {

    private WebView mWebView;
    private ProgressBar mPb;
    private static final int PROGRESS_RATIO = 1000;

    private String title;//标题
    private String url;//网页地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mWebView = (WebView) findViewById(R.id.webview);
        mPb = (ProgressBar) findViewById(R.id.webview_pb);
        initWebView();
        super.setHomeTrue();

    }

    private void initWebView() {
        //支持JS
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        //支持通过js打开新的窗口
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebView.getSettings().setAppCachePath(getFilesDir() + getPackageName() + "/cache");
        this.mWebView.getSettings().setAppCacheEnabled(true);
        this.mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        this.mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            /**
             * @param view The WebView that is initiating the callback.
             * @param url  The url of the page.
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                mPb.setProgress(progress);
                setProgress(progress * PROGRESS_RATIO);
                if (progress >= 80) {
                    mPb.setVisibility(View.GONE);
                } else {
                    mPb.setVisibility(View.VISIBLE);
                }
            }
        });
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         * 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小
         * 2、LayoutAlgorithm.SINGLE_COLUMN : 适应屏幕，内容将自动缩放
         */
        this.mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setSupportZoom(true);

    }

    @Override
    protected void initData() {
        getIntentData();//获取传递的信息
        this.mWebView.loadUrl(url);//加载URL
        this.setTitle(title);//设置标题
    }
    //获取传递的信息
    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            url = bundle.getString(BaseUrl.WEBVIEW_URL);
            title = bundle.getString(BaseUrl.WEBVIEW_TITLE);
        }
    }
    @Override
    protected void initListeners() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.keyBackProcess();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void keyBackProcess() {
         //如果竖屏
            if (this.mWebView.canGoBack()) {
                this.mWebView.goBack();
            } else {
                this.finish();
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWebView!=null){
            mWebView.destroy();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
