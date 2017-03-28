package com.bawei.dianshang.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bawei.dianshang.R;
import com.bawei.dianshang.active.Main5Activity;
import com.bawei.dianshang.apdate.SimpleApdate;
import com.bawei.dianshang.bean.Good;
import com.bawei.dianshang.bean.TiTleXia;
import com.bawei.dianshang.utils.Jiekou;
import com.bawei.dianshang.utils.Qingqiu;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/3/16.
 */
public class FeiFragment extends Fragment
{

    private View vv;
    private ImageView mianmo;
    private ImageView bubu;
    private ImageView ruye;
    private Button shihun;
    private Button qudou;
    private RecyclerView feilei;
    private List<TiTleXia> list;
    private List<TiTleXia> list1;
    private SimpleApdate sim1;
    private SimpleApdate sim;
    private DbUtils db;
    private List<Good> list2;
    Handler han=new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0:
                    sim = new SimpleApdate(getActivity(),list);
                    feilei.setLayoutManager(new GridLayoutManager(getActivity(),3));
                    feilei.setAdapter(sim);
                    sim.notifyDataSetChanged();
                    sim.setmOnItem(new SimpleApdate.OnItem() {
                        @Override
                        public void onduanan(View view, final int position) {
                            new AlertDialog.Builder(getActivity()).setTitle("系统提示")
                                    .setMessage("是否存入购物车！")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            list_good = new ArrayList<Good>();
                                            String efficacy = list.get(position).getEfficacy();
                                            String Goods_img = list.get(position).getGoods_img();
                                            String Market_price = list.get(position).getMarket_price();
                                            Good g=new Good();
                                            g.setImage(Goods_img);
                                            g.setPrice(Double.parseDouble(Market_price));
                                            g.setName(efficacy);
                                            list_good.add(g);
                                            try {
                                                db.saveAll(list_good);
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

                        @Override
                        public void onchangan(View view, int position)
                        {


                            list.remove(position);
                            sim.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "删除了", Toast.LENGTH_SHORT).show();

                        }
                    });
                    break;
                case 1:
                    sim1 = new SimpleApdate(getActivity(),list1);
                    feilei.setLayoutManager(new GridLayoutManager(getActivity(),3,RecyclerView.HORIZONTAL,false));
                    feilei.setAdapter(sim1);
                    sim1.notifyDataSetChanged();
                    sim1.setmOnItem(new SimpleApdate.OnItem() {
                        @Override
                        public void onduanan(View view, final int position) {
                            new AlertDialog.Builder(getActivity()).setTitle("系统提示")
                                    .setMessage("是否存入购物车！")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            list_good = new ArrayList<Good>();
                                            String efficacy = list1.get(position).getEfficacy();
                                            String Goods_img = list1.get(position).getGoods_img();
                                            String Market_price = list1.get(position).getMarket_price();
                                            Good g=new Good();
                                            g.setImage(Goods_img);
                                            g.setPrice(Double.parseDouble(Market_price));
                                            g.setName(efficacy);
                                            list_good.add(g);
                                            try {
                                                db.saveAll(list_good);
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

                        @Override
                        public void onchangan(View view, int position) {
                            list1.remove(position);
                            sim1.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "删除了"+position, Toast.LENGTH_SHORT).show();

                        }
                    });
                    break;
            }

        }
    };
    private List<Good> list_good;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vv = inflater.inflate(R.layout.item_feilei,null);

        db = DbUtils.create(getActivity(), "1502h", 1, null);
        try {
            db.createTableIfNotExist(Good.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

        initView();
        initDate();
        dianji();


        return vv;
    }

    private void initDate()
    {

        list = new ArrayList<>();
        list1 = new ArrayList<>();
        mianmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), Main5Activity.class);
                startActivity(intent);
            }
        });
        bubu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), Main5Activity.class);
                startActivity(intent);
            }
        });
        ruye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), Main5Activity.class);
                startActivity(intent);
            }
        });
        shihun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dianji();

            }
        });
        qudou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dianji1();

            }
        });


    }

    private void initView()
    {
        mianmo = (ImageView) vv.findViewById(R.id.ImageView_mianmo);
        bubu = (ImageView) vv.findViewById(R.id.ImageView_bubu);
        ruye = (ImageView) vv.findViewById(R.id.ImageView_ruye);
        shihun = (Button) vv.findViewById(R.id.Button_shihun);
        qudou = (Button) vv.findViewById(R.id.Button_qudou);
        feilei = (RecyclerView) vv.findViewById(R.id.RecyclerView_feilei);

    }
    public void dianji1()
    {
        new Qingqiu().Villeo(getActivity(), "http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id=9", new Jiekou()
        {
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
    public void dianji()
    {

        new Qingqiu().Villeo(getActivity(), "http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id=39", new Jiekou() {
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
                        list1.add(tt);
                        han.sendEmptyMessage(1);
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
