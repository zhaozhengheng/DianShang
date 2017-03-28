package com.bawei.dianshang.active;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.dianshang.R;
import com.bawei.dianshang.fragment.FeiFragment;
import com.bawei.dianshang.fragment.MineFragment;
import com.bawei.dianshang.fragment.ShopFragment;
import com.bawei.dianshang.fragment.ShouFragment;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener
{


    private FrameLayout mFrameLayout;
    private Fragment fragment;
    private ShouFragment shouFragment;
    private FeiFragment feiFragment;
    private ShouFragment shouFragment1;
    private MineFragment mineFragment;
    private ShopFragment shopFragment;
    private int[] arr=new int[]{R.mipmap.shouye1,R.mipmap.feilei1,R.mipmap.gouwuche1,R.mipmap.wode1};
    private int[] arr1=new int[]{R.mipmap.shou,R.mipmap.feilei,R.mipmap.gouwuche,R.mipmap.wode};
    private TextView[] tt;
    private ImageView[] image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        fragment = new Fragment();
        initView();
       /* if(shouFragment == null)
        {
            shouFragment = new ShouFragment();
        }*/
        addFragment(new ShouFragment());
        Color(0);
        initDate();
    }

    private void addFragment(Fragment f)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.FrameLayout,f);
       /* if(fragment !=null)
        {
            transaction.hide(fragment);
        }
        if(!f.isAdded())
        {
            transaction.add(R.id.FrameLayout,f);
        }
        transaction.show(f);*/
        transaction.commit();
        //fragment=f;

    }

    private void initDate()
    {

    }

    private void initView()
    {

        LinearLayout shou = (LinearLayout) findViewById(R.id.LinearLayout_shou);
        LinearLayout fei = (LinearLayout) findViewById(R.id.LinearLayout_fei);
        LinearLayout shop = (LinearLayout) findViewById(R.id.LinearLayout_shop);
        LinearLayout my = (LinearLayout) findViewById(R.id.LinearLayout_my);
        shou.setOnClickListener(this);
        fei.setOnClickListener(this);
        shop.setOnClickListener(this);
        my.setOnClickListener(this);
        TextView shou_TextView = (TextView) findViewById(R.id.TextView_shou);
        TextView fei_TextView = (TextView) findViewById(R.id.TextView_fei);
        TextView shop_TextView = (TextView) findViewById(R.id.TextView_shop);
        TextView mine_TextView = (TextView) findViewById(R.id.TextView_mine);
        tt = new TextView[]{shou_TextView,fei_TextView,shop_TextView,mine_TextView};
        ImageView shou_ImageView = (ImageView) findViewById(R.id.ImageView_shou);
        ImageView fei_ImageView = (ImageView) findViewById(R.id.ImageView_fei);
        ImageView shop_ImageView = (ImageView) findViewById(R.id.ImageView_shop);
        ImageView mine_ImageView = (ImageView) findViewById(R.id.ImageView_mine);
        image = new ImageView[]{shou_ImageView,fei_ImageView,shop_ImageView,mine_ImageView};
    }


    public void onClick(View v)
    {
switch (v.getId())
{
    case R.id.LinearLayout_shou:
    /*if(shouFragment == null)
    {
    shouFragment = new ShouFragment();
    }*/
        Color(0);
       addFragment(new ShouFragment());
        break;
    case R.id.LinearLayout_fei:
     /*  if(feiFragment==null)
       {
           feiFragment = new FeiFragment();

       }*/
        Color(1);
        addFragment(new FeiFragment());
        break;
    case R.id.LinearLayout_shop:
       /*if(shopFragment==null)
       {
           shopFragment = new ShopFragment();
       }*/
        Color(2);
        addFragment(new ShopFragment());
        break;
    case R.id.LinearLayout_my:
       /* if(mineFragment==null)
        {
            mineFragment = new MineFragment();

        }*/
        Color(3);
        addFragment(new MineFragment());
        break;
}
    }
    public void Color(int dext)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(dext==i)
            {
              tt[i].setTextColor(Color.RED);
                image[i].setImageResource(arr[i]);
            }else
            {
                tt[i].setTextColor(Color.BLACK);
                image[i].setImageResource(arr1[i]);
            }
        }
    }
}
