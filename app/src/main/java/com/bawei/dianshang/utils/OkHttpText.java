package com.bawei.dianshang.utils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by 1 on 2017/3/16.
 */
public class OkHttpText
{

    private static String string;

    public static String OkHttp(String uri)
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(uri).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException
            {
                string = response.body().string();


            }
        });
        return string;

    }
}
