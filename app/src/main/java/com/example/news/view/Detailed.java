package com.example.news.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class Detailed extends AppCompatActivity {

    TextView title, desc, date, source;
    WebView webView;
    ProgressBar loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        SharedPreferences sharedPreferences = getSharedPreferences("AppSettingPref", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Boolean isNightMode = sharedPreferences.getBoolean("NightMode", false);

        Intent intent = getIntent();
        String titleS = intent.getStringExtra("title");
        String sourceS = intent.getStringExtra("source");
        String timeS = intent.getStringExtra("time");
        String descS = intent.getStringExtra("desc");
        String imageUrlS = intent.getStringExtra("imageUrl");
        String urlS = intent.getStringExtra("url");

        if(isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //title.setBackgroundColor(getResources().getColor(R.color.white));
            //desc.setBackgroundColor(getResources().getColor(R.color.white));
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout_detailed);
        ConstraintLayout constraintLayout = findViewById(R.id.nested_layout_detailed);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        CardView cardView = findViewById(R.id.cardView_detailed);

        title = findViewById(R.id.tvTitle);
        source = findViewById(R.id.tvSource);
        date = findViewById(R.id.tvDate);
        desc = findViewById(R.id.tvDesc);
        ImageView imageView = findViewById(R.id.collapsingImage_detailed);

        Glide.with(this).load(imageUrlS).into(imageView);
        title.setText(titleS);
        source.setText(sourceS);
        date.setText(timeS);
        desc.setText(descS);

        AppBarLayout appBarLayout = findViewById(R.id.appbar_detailed);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow =true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(sourceS);
                    title.setVisibility(TextView.GONE);
                    source.setVisibility(TextView.GONE);
                    desc.setVisibility(TextView.GONE);
                    date.setVisibility(TextView.GONE);
                    cardView.setVisibility(CardView.VISIBLE);

                    isShow = true;

                }
                else if(isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    title.setVisibility(TextView.VISIBLE);
                    source.setVisibility(TextView.VISIBLE);
                    desc.setVisibility(TextView.VISIBLE);
                    date.setVisibility(TextView.VISIBLE);
                    cardView.setVisibility(CardView.GONE);
                    //cardView.setLayoutParams(new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, 0));
                    isShow = false;
                }
            }
        });

        webView = findViewById(R.id.webView);
        loader = findViewById(R.id.webViewLoader);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(urlS);
        if (webView.isShown()){
            loader.setVisibility(View.INVISIBLE);
        }
    }
}
