package com.example.pcc.doctintucrss;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NewsDetailActivity extends AppCompatActivity {

    MyWebView webView;
    ProgressDialog dialog;
    String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        webView=(MyWebView)findViewById(R.id.wvData);
        String url=getIntent().getStringExtra("link");
        if(url!=null){
            dialog=new ProgressDialog(this);
            dialog.setMessage("Loading");
            dialog.setCancelable(false);
            dialog.show();
            webView.setWebViewClient(onWebViewLoaded);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//set size image in webview
           // webView.loadUrl("file:///android_asset/demo.html");
            webView.loadUrl(url);
            //get html

        }


    }
    private WebViewClient onWebViewLoaded=new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dialog.dismiss();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            dialog.dismiss();
            Toast.makeText(NewsDetailActivity.this,"error",Toast.LENGTH_SHORT).show();
        }
    };
}
