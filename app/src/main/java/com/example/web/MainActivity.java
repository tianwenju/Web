package com.example.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.util.List;

public class MainActivity extends Activity {
    private WebView mWebView;
    private String TMP_URL = "http://laodongjianguan.chanlytech.com:8088/laodongjianguan/index.php/daohang/daohang/f";
    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final static int FILECHOOSER_RESULTCODE = 1;// 表单的结果回调</span>
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(TMP_URL);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }
        });
        FunctionOptions options = new FunctionOptions.Builder()
                .setType(FunctionConfig.TYPE_IMAGE)
                .setMaxB(202400)
                .setEnableQualityCompress(true) //是否启质量压缩
                .setCompress(true)
                .setSelectMode(FunctionConfig.MODE_SINGLE)
                .setGrade(Luban.THIRD_GEAR)
                .create();
        PictureConfig.getInstance().init(options);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             FileChooserParams fileChooserParams) {
                mUploadCallbackAboveL = filePathCallback;
                // take();

                PictureConfig.getInstance().openPhoto(MainActivity.this, new PictureConfig.OnSelectResultCallback() {
                    @Override
                    public void onSelectSuccess(List<LocalMedia> list) {

                    }

                    @Override
                    public void onSelectSuccess(LocalMedia localMedia) {

                        mUploadCallbackAboveL.onReceiveValue(new Uri[]{Uri.fromFile(new File(localMedia.getCompressPath()))});
                        mUploadCallbackAboveL=null;
                    }
                });

                return true;
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                PictureConfig.getInstance().openPhoto(MainActivity.this, new PictureConfig.OnSelectResultCallback() {
                    @Override
                    public void onSelectSuccess(List<LocalMedia> list) {

                    }

                    @Override
                    public void onSelectSuccess(LocalMedia localMedia) {

                        mUploadMessage.onReceiveValue(Uri.fromFile(new File(localMedia.getCompressPath())));
                        mUploadMessage=null;
                    }
                });
                // take();
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                PictureConfig.getInstance().openPhoto(MainActivity.this, new PictureConfig.OnSelectResultCallback() {
                    @Override
                    public void onSelectSuccess(List<LocalMedia> list) {

                    }

                    @Override
                    public void onSelectSuccess(LocalMedia localMedia) {

                        mUploadMessage.onReceiveValue(Uri.fromFile(new File(localMedia.getCompressPath())));
                        mUploadMessage=null;
                    }
                });
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                PictureConfig.getInstance().openPhoto(MainActivity.this, new PictureConfig.OnSelectResultCallback() {
                    @Override
                    public void onSelectSuccess(List<LocalMedia> list) {

                    }

                    @Override
                    public void onSelectSuccess(LocalMedia localMedia) {

                        mUploadMessage.onReceiveValue(Uri.fromFile(new File(localMedia.getCompressPath())));
                        mUploadMessage=null;
                    }
                });
            }
        });
    }

}
