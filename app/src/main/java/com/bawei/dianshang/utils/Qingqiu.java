package com.bawei.dianshang.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.dianshang.bean.TiTleXia;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by 1 on 2017/3/23.
 */
public class Qingqiu
{


    public void Villeo(Context context, String uri, final Jiekou jiekou)
    {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        jiekou.chenggong(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                jiekou.shibai(error);
            }
        });
        mQueue.add(stringRequest);
    }

}
