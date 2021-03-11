package com.faysol.ehomevisit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.faysol.ehomevisit.SharedPrefrence_classes.Shared_Pref;

import java.util.Locale;

public class WebActivity extends AppCompatActivity {
        String a,b,c;
    WebView webView;

Shared_Pref shared_pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web);
        Bundle bundle = getIntent().getExtras();
        shared_pref=new Shared_Pref(WebActivity.this);
        String patient_number = "";
        String a = bundle.getString("id", patient_number);
        String pass = "";
        String b = bundle.getString("password", pass);
        String c = "https://www.ehausbesuch.de/index.cgi?app=welcome&userid=" + a + "&passwort=" + b ;

        webView = findViewById(R.id.webView);
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(c);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {


            }
        });


    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else
        {
            Intent i = new Intent(WebActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }

public  void  logout_user()
{
    shared_pref.set_loginstatus(false);
    Intent intent = new Intent(WebActivity.this,MainActivity.class);
    startActivity(intent);
    finish();
}

    public void Onclick_UserLogout(View view) {
        logout_user();
    }
}