package com.bawei.dianshang.apdate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.dianshang.R;

import java.util.List;

/**
 * Created by 1 on 2017/3/27.
 */
public class MineApdate extends RecyclerView.Adapter<MyPade>
{
    private Context context;
    private List<String> list;

    public MineApdate(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public MineApdate()
    {
        super();
    }

    @Override
    public MyPade onCreateViewHolder(ViewGroup parent, int viewType)
    {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_mm, parent, false);
        MyPade myPade =new MyPade(view);

        return myPade;
    }


    @Override
    public void onBindViewHolder(MyPade holder, int position)
    {
        holder.mm.setText(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
class MyPade extends RecyclerView.ViewHolder{

    TextView mm;
    public MyPade(View itemView)
    {
        super(itemView);
         mm = (TextView) itemView.findViewById(R.id.TextView_mm);
    }
}
