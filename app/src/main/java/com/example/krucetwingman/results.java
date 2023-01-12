package com.example.krucetwingman;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class results extends AppCompatActivity {
    Button results1, results2, results3, results4, results5, results6, results7, results8;
    WebView view;
    FirebaseRemoteConfig mFirebaseRemoteConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_results);
        results1 = findViewById(R.id.results_1);
        results2 = findViewById(R.id.results_2);
        results3 = findViewById(R.id.results_3);
        results4 = findViewById(R.id.results_4);
        results5 = findViewById(R.id.results_5);
        results6 = findViewById(R.id.results_6);
        results7 = findViewById(R.id.results_7);
        results8 = findViewById(R.id.results_8);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(10)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);


        view = findViewById(R.id.results_webview);

        WebSettings setings = view.getSettings();
        view.getSettings().setAppCacheEnabled(true);
        view.getSettings().setAppCachePath("/data/data" + getPackageName() + "/cache");
        view.getSettings().setSaveFormData(true);
        view.getSettings().setDatabaseEnabled(true);
        view.getSettings().setDomStorageEnabled(true);
        CookieManager.getInstance().acceptCookie();
        setings.setJavaScriptEnabled(true);
        setings.setDomStorageEnabled(true);
        setings.setLoadWithOverviewMode(true);
        setings.setAllowContentAccess(true);
        setings.setAppCacheEnabled(true);
        setings.setUseWideViewPort(true);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.home:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.studentportal:
                        Intent portal = new Intent(getApplicationContext(),Student_portal.class);
                        startActivity(portal);
                        break;
                    case R.id.studentprofile:
                        Intent profile = new Intent(getApplicationContext(),Main_student_profile.class);
                        startActivity(profile);
                        break;

                }
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });


        results1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1 = null;
                url1 = (mFirebaseRemoteConfig.getString("results_url1"));
                view.loadUrl("https://upiqpbank.com/kvrrms/home/results/BTech-IVandVISemesterExaminationsSeptember-15022022");
                results1.setVisibility(View.GONE);
                results2.setVisibility(View.GONE);
                results3.setVisibility(View.GONE);
                results4.setVisibility(View.GONE);
                results5.setVisibility(View.GONE);
                results6.setVisibility(View.GONE);
                results7.setVisibility(View.GONE);
                results8.setVisibility(View.GONE);
                EditText user = loginActivity.userEmail;
                view.loadUrl("javascript:(function(){document.getElementById('hallticket_no').value = '" + user + "'; ;}) ()");
                view.loadUrl("javascript : (function() { var z = document.getElementById('form_submit').click();})()");
                view.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent link = new Intent(Intent.ACTION_VIEW);
                        link.setData(Uri.parse(url));
                        startActivity(link);

                    }
                });
            }
        });


        results2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2 = null;
                url2 = (mFirebaseRemoteConfig.getString("results_url2"));
                view.loadUrl(url2);
                results1.setVisibility(View.GONE);
                results2.setVisibility(View.GONE);
                results3.setVisibility(View.GONE);
                results4.setVisibility(View.GONE);
                results5.setVisibility(View.GONE);
                results6.setVisibility(View.GONE);
                results7.setVisibility(View.GONE);
                results8.setVisibility(View.GONE);
                EditText user = loginActivity.userEmail;
                view.loadUrl("javascript:(function(){document.getElementById('hallticket_no').value = 'user'}) ()");
                view.loadUrl("javascript : (function() { var z = document.getElementById('form_submit').click();})()");
                view.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent link = new Intent(Intent.ACTION_VIEW);
                        link.setData(Uri.parse(url));
                        startActivity(link);
                    }
                });

            }
        });

        results3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url3 = null;
                url3 = (mFirebaseRemoteConfig.getString("results_url3"));
                view.loadUrl(url3);
                results1.setVisibility(View.GONE);
                results2.setVisibility(View.GONE);
                results3.setVisibility(View.GONE);
                results4.setVisibility(View.GONE);
                results5.setVisibility(View.GONE);
                results6.setVisibility(View.GONE);
                results7.setVisibility(View.GONE);
                results8.setVisibility(View.GONE);

                EditText user = loginActivity.userEmail;
                view.loadUrl("javascript:(function(){document.getElementById('hallticket_no').value = '" + user + "'; ;}) ()");
                view.loadUrl("javascript : (function() { var z = document.getElementById('form_submit').click();})()");
                view.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent link = new Intent(Intent.ACTION_VIEW);
                        link.setData(Uri.parse(url));
                        startActivity(link);
                    }
                });

            }
        });

        results4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url4 = null;
                url4 = (mFirebaseRemoteConfig.getString("results_url4"));
                view.loadUrl(url4);
                results1.setVisibility(View.GONE);
                results2.setVisibility(View.GONE);
                results3.setVisibility(View.GONE);
                results4.setVisibility(View.GONE);
                results5.setVisibility(View.GONE);
                results6.setVisibility(View.GONE);
                results7.setVisibility(View.GONE);
                results8.setVisibility(View.GONE);

                EditText user = loginActivity.userEmail;
                view.loadUrl("javascript:(function(){document.getElementById('hallticket_no').value = '" + user + "'; ;}) ()");
                view.loadUrl("javascript : (function() { var z = document.getElementById('form_submit').click();})()");
                view.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent link = new Intent(Intent.ACTION_VIEW);
                        link.setData(Uri.parse(url));
                        startActivity(link);
                    }
                });

            }
        });

        results5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url5 = null;
                url5 = (mFirebaseRemoteConfig.getString("results_url5"));
                view.loadUrl(url5);
                results1.setVisibility(View.GONE);
                results2.setVisibility(View.GONE);
                results3.setVisibility(View.GONE);
                results4.setVisibility(View.GONE);
                results5.setVisibility(View.GONE);
                results6.setVisibility(View.GONE);
                results7.setVisibility(View.GONE);
                results8.setVisibility(View.GONE);

                EditText user = loginActivity.userEmail;
                view.loadUrl("javascript:(function(){document.getElementById('hallticket_no').value = '" + user + "'; ;}) ()");
                view.loadUrl("javascript : (function() { var z = document.getElementById('form_submit').click();})()");
                view.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent link = new Intent(Intent.ACTION_VIEW);
                        link.setData(Uri.parse(url));
                        startActivity(link);
                    }
                });

            }
        });

        results6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url6 = null;
                url6 = (mFirebaseRemoteConfig.getString("results_url6"));
                view.loadUrl(url6);
                results1.setVisibility(View.GONE);
                results2.setVisibility(View.GONE);
                results3.setVisibility(View.GONE);
                results4.setVisibility(View.GONE);
                results5.setVisibility(View.GONE);
                results6.setVisibility(View.GONE);
                results7.setVisibility(View.GONE);
                results8.setVisibility(View.GONE);

                EditText user = loginActivity.userEmail;
                view.loadUrl("javascript:(function(){document.getElementById('hallticket_no').value = '" + user + "'; ;}) ()");
                view.loadUrl("javascript : (function() { var z = document.getElementById('form_submit').click();})()");
                view.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent link = new Intent(Intent.ACTION_VIEW);
                        link.setData(Uri.parse(url));
                        startActivity(link);
                    }
                });
            }
        });

        results7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url7 = null;
                url7 = (mFirebaseRemoteConfig.getString("results_url7"));
                view.loadUrl(url7);
                results1.setVisibility(View.GONE);
                results2.setVisibility(View.GONE);
                results3.setVisibility(View.GONE);
                results4.setVisibility(View.GONE);
                results5.setVisibility(View.GONE);
                results6.setVisibility(View.GONE);
                results7.setVisibility(View.GONE);
                results8.setVisibility(View.GONE);
                EditText user = loginActivity.userEmail;
                view.loadUrl("javascript:(function(){document.getElementById('hallticket_no').value = '" + user + "'; ;}) ()");
                view.loadUrl("javascript : (function() { var z = document.getElementById('form_submit').click();})()");
                view.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent link = new Intent(Intent.ACTION_VIEW);
                        link.setData(Uri.parse(url));
                        startActivity(link);
                    }
                });

            }
        });

        results8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url8 = null;
                url8 = (mFirebaseRemoteConfig.getString("results_url8"));
                view.loadUrl(url8);
                results1.setVisibility(View.GONE);
                results2.setVisibility(View.GONE);
                results3.setVisibility(View.GONE);
                results4.setVisibility(View.GONE);
                results5.setVisibility(View.GONE);
                results6.setVisibility(View.GONE);
                results7.setVisibility(View.GONE);
                results8.setVisibility(View.GONE);

                EditText user = loginActivity.userEmail;
                view.loadUrl("javascript:(function(){document.getElementById('hallticket_no').value = '" + user + "'; ;}) ()");
                view.loadUrl("javascript : (function() { var z = document.getElementById('form_submit').click();})()");
                view.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent link = new Intent(Intent.ACTION_VIEW);
                        link.setData(Uri.parse(url));
                        startActivity(link);
                    }
                });


            }
        });
    }
}
