package com.bawei.dianshang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.dianshang.R;
import com.bawei.dianshang.active.Main2Activity;
import com.bawei.dianshang.active.Main3Activity;

/**
 * Created by 1 on 2017/3/16.
 */
public class Three extends Fragment
{

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vv = inflater.inflate(R.layout.item3,null);
        ImageView tiyan = (ImageView) vv.findViewById(R.id.ImageView_tiyan);
        tiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),Main3Activity.class);
               startActivity(intent);
            }
        });
        return vv;
    }
}
