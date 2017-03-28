package com.bawei.dianshang.active;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.bawei.dianshang.R;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        WebView ww= (WebView) findViewById(R.id.WebView);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        ww.loadUrl(name);


    }
}
