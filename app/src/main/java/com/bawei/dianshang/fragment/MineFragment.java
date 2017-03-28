package com.bawei.dianshang.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dianshang.R;
import com.bawei.dianshang.active.Main6Activity;
import com.bawei.dianshang.active.Main7Activity;
import com.bawei.dianshang.active.MainActivity;
import com.bawei.dianshang.apdate.MineApdate;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/3/16.
 */
public class MineFragment extends Fragment
{

    private View vv;

    private ImageView qq;
    private TextView denglu;
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private DisplayImageOptions dd;
    private RecyclerView mine;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vv = inflater.inflate(R.layout.item_mine,null);
        initView();
        initDate();
        List<String> list=new ArrayList<String>();
        list.add("我的订单");
        list.add("邀请有礼");
        list.add("联系客服");

        list.add("我的抽奖单");
        list.add("我的位置");
        dd = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(180)).build();
        mTencent = Tencent.createInstance(APP_ID,getActivity().getApplicationContext());
      /*  mine.setLayoutManager(new LinearLayoutManager(getActivity()));
        MineApdate mineApdate = new MineApdate(getActivity(),list);
        mine.setAdapter(mineApdate);*/




        return vv;
    }





    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initDate()
    {
qq.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View v)
    {
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(MineFragment.this,"all", mIUiListener);
    }
});
    }

    private void initView()
    {
        //mine = (RecyclerView) vv.findViewById(R.id.RelativeLayout_mine);
        qq = (ImageView) vv.findViewById(R.id.imageView_qq);
        denglu = (TextView) vv.findViewById(R.id.TextView_denglu);
        TextView dingdan = (TextView) vv.findViewById(R.id.TextView_dingdan);
        TextView yaoqing = (TextView) vv.findViewById(R.id.TextView_yaoqing);
        TextView kefu = (TextView) vv.findViewById(R.id.TextView_kefu);
        TextView choujiang = (TextView) vv.findViewById(R.id.TextView_choujiang);
        TextView weizhi = (TextView) vv.findViewById(R.id.TextView_weizhi);
        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), Main7Activity.class);
                startActivity(intent);
            }
        });
        weizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), Main6Activity.class);
                startActivity(intent);
            }
        });
    }
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {

                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getActivity().getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        JSONObject object=(JSONObject) response;
                        String nickname = object.optString("nickname");
                        String figureurl_qq_2 = object.optString("figureurl_qq_2");
                        denglu.setText(nickname);

                        ImageLoader.getInstance().displayImage(figureurl_qq_2,qq,dd);
                    }

                    @Override
                    public void onError(UiError uiError) {


                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {

            Toast.makeText(getActivity(), "授权取消", Toast.LENGTH_SHORT).show();

        }

    }
}

