package com.example.vaibhav.booksearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URL;

public class BookReadWebView extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_read_web_view);

        String url=getIntent().getStringExtra("READWEBURL");
    webView=(WebView)findViewById(R.id.ReadBookOnline);
/*
        webView.loadUrl("https://play.google.com/books/reader?id=5kQvCwAAQBAJ&hl&printsec=frontcover&source=gbs_api&pg=GBS.PA3");
*/
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        Toast.makeText(this, "URL:" + url , Toast.LENGTH_SHORT).show();
        webView.loadUrl("https://play.google.com/books/reader?id=5kQvCwAAQBAJ&hl&printsec=frontcover&source=gbs_api&pg=GBS.PA3");
    }
}
