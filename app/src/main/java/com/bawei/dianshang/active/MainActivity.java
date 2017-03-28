package com.bawei.dianshang.active;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bawei.dianshang.R;

public class MainActivity extends AppCompatActivity {
    Handler han=new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

        }
    };
    private SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         initView();
        new Thread(){
           @Override
           public void run() {
               super.run();
               try {
                   sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               preferences = getSharedPreferences("name", MODE_PRIVATE);
               boolean fals = preferences.getBoolean("fals", false);
               if(fals)
               {
                   Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                   startActivity(intent);
               }else
               {
                   preferences = getSharedPreferences("name", MODE_PRIVATE);
                   SharedPreferences.Editor edit = preferences.edit();
                   edit.putBoolean("fals",true);
                   edit.commit();
                   Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                   startActivity(intent);
               }



           }
       }.start();


    }
    private void initView()
    {
        ImageView qishi = (ImageView) findViewById(R.id.ImageView_qishi);
    }
}
