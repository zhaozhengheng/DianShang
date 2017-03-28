package com.bawei.dianshang.active;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.dianshang.R;
import com.bawei.dianshang.apdate.SimpleApdate;
import com.bawei.dianshang.bean.Good;
import com.bawei.dianshang.bean.TiTleXia;
import com.bawei.dianshang.utils.Jiekou;
import com.bawei.dianshang.utils.Qingqiu;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {


    private GoogleApiClient client;
    private List<TiTleXia> list;
     Handler han =new Handler(){
         @Override
         public void handleMessage(Message msg)
         {
             super.handleMessage(msg);
             sim = new SimpleApdate(Main5Activity.this,list);
             re.setLayoutManager(new GridLayoutManager(Main5Activity.this,2));
             re.setAdapter(sim);
             sim.setmOnItem(new SimpleApdate.OnItem() {
                 @Override
                 public void onduanan(View view, final int position)
                 {
                     new AlertDialog.Builder(Main5Activity.this).setTitle("系统提示")
                             .setMessage("是否存入购物车！")
                             .setPositiveButton("确定", new DialogInterface.OnClickListener()
                             {

                                 public void onClick(DialogInterface dialog, int which)
                                 {
                                     List<Good> list2=new ArrayList<Good>();
                                 String goods_img = list.get(position).getGoods_img();
                                 double price = Double.parseDouble(list.get(position).getMarket_price());
                                 String name = list.get(position).getEfficacy();
                                 Good g=new Good();
                                 g.setImage(goods_img);
                                 g.setPrice(price);
                                 g.setName(name);
                                 list2.add(g);
                                 try {
                                 db.saveAll(list2);
                             } catch (DbException e) {
                                 e.printStackTrace();
                             }
                             }
                             }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {

                         }
                     }).show();
                 }


                 public void onchangan(View view, int position)
                 {
                     list.remove(position);
                     sim.notifyDataSetChanged();
                     Toast.makeText(Main5Activity.this, "删除了", Toast.LENGTH_SHORT).show();
                 }
             });



         }
     };
    private SimpleApdate sim;
    private RecyclerView re;
    private DbUtils db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        db = DbUtils.create(this, "1502h", 1, null);
        try {
            db.createTableIfNotExist(Good.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        initDate();
        re = (RecyclerView) findViewById(R.id.RecyclerView_5);




    }

    public void initDate()
    {
        list = new ArrayList<TiTleXia>();
        Qingqiu qingqiu = new Qingqiu();
        qingqiu.Villeo(this, "http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id=9", new Jiekou() {
            @Override
            public void chenggong(String response)
            {
                try {
                            JSONObject js=new JSONObject(response);
                            JSONArray data = js.optJSONArray("data");
                            String[] arr=new String[data.length()];
                            for(int i=0;i<arr.length;i++)
                            {
                                arr[i]=data.optString(i);
                                JSONObject jj=new JSONObject(arr[i]);
                                String goods_img = jj.optString("goods_img");
                                String efficacy = jj.optString("efficacy");
                                String shop_price = jj.optString("shop_price");
                                TiTleXia tt=new TiTleXia();
                                tt.setMarket_price(shop_price);
                                tt.setGoods_img(goods_img);
                                tt.setEfficacy(efficacy);
                                list.add(tt);
                                han.sendEmptyMessage(0);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            }

            @Override
            public void shibai(VolleyError error) {

            }
        });
    }


}
