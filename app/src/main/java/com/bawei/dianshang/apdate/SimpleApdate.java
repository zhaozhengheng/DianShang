package com.bawei.dianshang.apdate;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.dianshang.R;
import com.bawei.dianshang.bean.TiTleXia;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 1 on 2017/3/17.
 */
public class SimpleApdate extends RecyclerView.Adapter<MyHodleView>
{
    DisplayImageOptions dd=new DisplayImageOptions.Builder().build();
     private Context context;
     private List<TiTleXia> list;
     private LayoutInflater Inflater;
    public interface  OnItem{
        void onduanan(View view,int position);
        void onchangan(View view, int position);
    }
    private OnItem mOnItem;
    public void setmOnItem(OnItem mOnItem)
    {
        this.mOnItem = mOnItem;
    }

    /* public interface  OnItemClicklisetn{
            void onItemlisten(View view,int position);
            void onlongItemlisten(View view,int position);
    }

        private OnItemClicklisetn mOnItemClicklisetn;
        public void setOnItemClicklisetn(OnItemClicklisetn listent)
        {
        this.mOnItemClicklisetn = listent;
        }*/
    public SimpleApdate(Context context, List<TiTleXia> list) {
        this.context = context;
        this.list = list;
        Inflater = LayoutInflater.from(context);
    }

    public SimpleApdate()
    {
        super();
    }


    public MyHodleView onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=Inflater.inflate( R.layout.item_recycle,parent,false);
        MyHodleView myHodleView = new MyHodleView(view);
        return myHodleView;
    }


    public void onBindViewHolder(final MyHodleView holder,final int position)
    {
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),holder.ImageView_recycle,dd);
        holder.TextView_recycle1.setText(list.get(position).getEfficacy());
        holder.TextView_recycle2.setText("￥"+list.get(position).getMarket_price());
        holder.TextView_recycle2.setTextColor(Color.RED);
        if(mOnItem!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    mOnItem.onduanan(holder.itemView,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItem.onchangan(holder.itemView,position);
                    return true;
                }
            });
        }

    }
    public int getItemCount()
    {
        return list.size();
    }

}
class  MyHodleView extends RecyclerView.ViewHolder
{
    ImageView ImageView_recycle;
    TextView TextView_recycle1;
    TextView TextView_recycle2;
    public MyHodleView(View itemView)
    {
        super(itemView);
        ImageView_recycle = (ImageView)itemView.findViewById(R.id.ImageView_recycle);
        TextView_recycle1 = (TextView) itemView.findViewById(R.id.TextView_recycle1);
        TextView_recycle2 = (TextView) itemView.findViewById(R.id.TextView_recycle2);
    }

}
