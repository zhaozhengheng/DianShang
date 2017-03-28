package com.bawei.dianshang.active;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bawei.dianshang.R;
import com.bawei.dianshang.fragment.One;
import com.bawei.dianshang.fragment.Three;
import com.bawei.dianshang.fragment.Two;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity
{

    private ViewPager viewPage;
    private LinearLayout mLinearLayout;
    private List<Fragment> list;
    private List<ImageView> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        jihe();
        initView();
        yuan();
        initDate();


    }

    private void yuan()
    {
        list1 = new ArrayList<ImageView>();
        for(int i=0;i<list.size();i++)
        {
            ImageView im=new ImageView(this);
            if(i==0)
            {
             im.setImageResource(R.drawable.aa);
            }else
            {
                im.setImageResource(R.drawable.bb);
            }
            im.setLayoutParams(new LinearLayout.LayoutParams(20,20));
            im.setScaleType(ImageView.ScaleType.FIT_XY);
            list1.add(im);
            mLinearLayout.addView(im);
        }
    }

    private void jihe()
    {
        list = new ArrayList<Fragment>();
        list.add(new One());
        list.add(new Two());
        list.add(new Three());
    }

    private void initDate()
    {
        viewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position)
            {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {

                for(int a=0;a<list.size();a++)
                {
                    ImageView view = list1.get(a);
                    if(a==position)
                    {
                        view.setImageResource(R.drawable.aa);
                    }else
                    {
                        view.setImageResource(R.drawable.bb);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView()
    {
        viewPage = (ViewPager) findViewById(R.id.ViewPager);
        mLinearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
    }

}
