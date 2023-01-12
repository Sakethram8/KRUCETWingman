package com.example.krucetwingman;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    WebView view;
    NavigationView navView;
    DrawerLayout drawer;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);
        fAuth = FirebaseAuth.getInstance();
        view = findViewById(R.id.main_webview);
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
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setBuiltInZoomControls(true);
        String url;
        url = "https://kru.ac.in/";


        WebViewClient WebClient = new WebViewClient();
         view.loadUrl("kru.ac.in");
        view.setWebViewClient(WebClient);
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
                    case R.id.drawer_logout:
                        fAuth.signOut();
                        Intent LOGOUT =new Intent(getApplicationContext(),loginActivity.class);
                        startActivity(LOGOUT);
                        finish();

                }
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }
}