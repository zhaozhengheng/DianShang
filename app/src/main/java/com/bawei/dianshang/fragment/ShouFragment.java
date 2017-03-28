package com.bawei.dianshang.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.bawei.dianshang.active.Main3Activity;
import com.bawei.dianshang.active.Main4Activity;
import com.bawei.dianshang.apdate.SimpleApdate;
import com.bawei.dianshang.bean.Good;
import com.bawei.dianshang.bean.TiTleXia;

import com.bawei.dianshang.R;
import com.bawei.dianshang.bean.TitleLunbo;

import com.bawei.dianshang.utils.GlideImageLoader;
import com.bawei.dianshang.utils.OkHttpText;
import com.bumptech.glide.Glide;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/3/16.
 */
public class ShouFragment extends Fragment {

    private View vv;
    private List<TitleLunbo> list;
    private List<String> list1;
    private List<String> list3;
    private ViewPager pp1;
    private LinearLayout ll;
    private List<ImageView> list2;
    private DisplayImageOptions dd;
    Handler han = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what)
            {
                case 0:
                    int item = pp1.getCurrentItem();
                    ++item;
                    pp1.setCurrentItem(item);
                    han.sendEmptyMessageDelayed(0, 1000);
                    break;
                case 1:
                    Banner banner = (Banner)vv.findViewById(R.id.banner);
                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    banner.setImages(list3);
                    banner.isAutoPlay(false);
                    //取消圆点
                    banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                    break;
                case 2:
                    ImageLoader.getInstance().displayImage(list.get(4).getImage(),zuihou,dd);
                    break;
            }


        }
    };
    private LinearLayout l2;
    private LinearLayout l1;
    private LinearLayout l3;
    private LinearLayout l4;
    private RecyclerView mRecyclerView;
    private List<TiTleXia> list_xia;
    private SimpleApdate sim;
    private ImageView zuihou;
    private DbUtils db;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vv = inflater.inflate(R.layout.item_shouye, null);
        db = DbUtils.create(getActivity(), "1502h", 1, null);
        try {
            db.createTableIfNotExist(Good.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        list = new ArrayList<TitleLunbo>();
        list1 = new ArrayList<String>();
        list3 = new ArrayList<String>();
        list_xia = new ArrayList<TiTleXia>();
        dd = new DisplayImageOptions.Builder().build();
        initView();
        initDate();
        jianting();
        yuan();
        han.sendEmptyMessageDelayed(0, 1000);




        return vv;
    }

    private void jianting() {
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://m.yunifang.com/yunifang/web/h/11_cohere_reg.html";
                Intent intent = new Intent(getActivity(), Main4Activity.class);
                intent.putExtra("name", uri);
                startActivity(intent);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://m.yunifang.com/yunifang/web/help/cash";
                Intent intent = new Intent(getActivity(), Main4Activity.class);
                intent.putExtra("name", uri);
                startActivity(intent);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://m.yunifang.com/yunifang/web/memberRights/myMemberGift";
                Intent intent = new Intent(getActivity(), Main4Activity.class);
                intent.putExtra("name", uri);
                startActivity(intent);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setTitle("系统提示")
                        .setMessage("请先进行登录！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


            }
        });
    }



    private void initDate() {
        RequestParams re = new RequestParams("http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447");
        x.http().get(re, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject js = new JSONObject(result);
                    JSONObject data = js.optJSONObject("data");
                    JSONObject activityInfo = data.optJSONObject("activityInfo");
                    JSONArray activityInfoList = activityInfo.optJSONArray("activityInfoList");
                    String[] arr2=new String[activityInfoList.length()];
                    for(int a1=0;a1<arr2.length;a1++)
                    {
                        arr2[a1]=activityInfoList.optString(a1);
                        JSONObject je=new JSONObject(arr2[a1]);
                        String img = je.optString("activityImg");
                         list3.add(img);
                        han.sendEmptyMessage(1);
                    }




                    JSONArray defaultGoodsList = data.optJSONArray("defaultGoodsList");
                    String[] arr1 = new String[defaultGoodsList.length()];
                    for(int a=0;a<arr1.length;a++)
                    {
                        arr1[a]=defaultGoodsList.optString(a);
                        JSONObject jsonObject = new JSONObject(arr1[a]);
                        String efficacy = jsonObject.optString("efficacy");
                        String goods_img = jsonObject.optString("goods_img");
                        String market_price = jsonObject.optString("market_price");
                        TiTleXia titlexia = new TiTleXia();
                        titlexia.setEfficacy(efficacy);
                        titlexia.setGoods_img(goods_img);
                        titlexia.setMarket_price(market_price);
                        list_xia.add(titlexia);

                    }
                    JSONArray ad1 = data.optJSONArray("ad1");
                    String[] arr = new String[ad1.length()];
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = ad1.optString(i);
                        JSONObject jj = new JSONObject(arr[i]);
                        String image = jj.optString("image");
                        list1.add(image);

                        String ad_type_dynamic_data = jj.optString("ad_type_dynamic_data");
                        TitleLunbo title = new TitleLunbo();
                        title.setImage(image);
                        title.setUri(ad_type_dynamic_data);
                        list.add(title);
                        han.sendEmptyMessage(2);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                sim = new SimpleApdate(getActivity(),list_xia);
                mRecyclerView.setAdapter(sim);
                sim.notifyDataSetChanged();
                LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(linearLayoutManager);

                sim.setmOnItem(new SimpleApdate.OnItem() {
                    @Override
                    public void onduanan(View view, final int position)
                    {
                        new AlertDialog.Builder(getActivity()).setTitle("系统提示")
                                .setMessage("是否存入购物车！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        List<Good> list2=new ArrayList<Good>();
                                        String goods_img = list_xia.get(position).getGoods_img();
                                        double price = Double.parseDouble(list_xia.get(position).getMarket_price());
                                        String name = list_xia.get(position).getEfficacy();
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

                    @Override
                    public void onchangan(View view, int position)
                    {
                        list_xia.remove(position);
                        sim.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "删除了", Toast.LENGTH_SHORT).show();
                    }
                });

                shipei();







            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private void initView() {


        pp1 = (ViewPager) vv.findViewById(R.id.ViewPager_pp);
        ll = (LinearLayout) vv.findViewById(R.id.LinearLayout_pp);
        l1 = (LinearLayout) vv.findViewById(R.id.LinearLayout1);
        l2 = (LinearLayout) vv.findViewById(R.id.LinearLayout2);
        l3 = (LinearLayout) vv.findViewById(R.id.LinearLayout3);
        l4 = (LinearLayout) vv.findViewById(R.id.LinearLayout4);
        zuihou = (ImageView) vv.findViewById(R.id.ImageView_zuihou);
        mRecyclerView = (RecyclerView) vv.findViewById(R.id.RecyclerView);

    }

    public void shipei() {
        pp1.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = View.inflate(getActivity(), R.layout.item, null);
                ImageView shouye = (ImageView) view.findViewById(R.id.ImageView_shouye);
                int i = position % list1.size();
                ImageLoader.getInstance().displayImage(list1.get(i), shouye, dd);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        pp1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int a = 0; a < 6; a++) {
                    ImageView imag = list2.get(a);

                    if (a == position % 6) {
                        imag.setImageResource(R.drawable.aa);
                    } else {
                        imag.setImageResource(R.drawable.bb);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void yuan() {
        list2 = new ArrayList<ImageView>();
        for (int i = 0; i < 6; i++) {
            ImageView im = new ImageView(getActivity());
            if (i == 0) {
                im.setImageResource(R.drawable.aa);
            } else {
                im.setImageResource(R.drawable.bb);
            }
            im.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
            im.setScaleType(ImageView.ScaleType.FIT_XY);
            ll.addView(im);
            list2.add(im);


        }


    }

}
