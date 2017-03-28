package com.bawei.dianshang.apdate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dianshang.R;
import com.bawei.dianshang.utils.GroupInfo;
import com.bawei.dianshang.utils.ProductInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/16.
 */

public class ShopCarExpandableListviewAdapter extends BaseExpandableListAdapter {
    private List<GroupInfo> groups;
    private Map<String, List<ProductInfo>> children;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private DisplayImageOptions dd;

    public ShopCarExpandableListviewAdapter(List<GroupInfo> groups, Map<String, List<ProductInfo>> children, Context context) {
        super();
        this.groups = groups;
        this.children = children;
        this.context = context;
        dd = new DisplayImageOptions.Builder().build();
    }
    //设置复选框接口
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }
    //设置增删接口
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId = groups.get(groupPosition).getId();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<ProductInfo> childs = children.get(groups.get(groupPosition).getId());
        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder gholder;
        if (convertView == null) {
            gholder = new GroupHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopcart_group, null);
            gholder.cb_check = (CheckBox) convertView.findViewById(R.id.determine_chekbox);
            gholder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_source_name);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupHolder) convertView.getTag();
        }
        final GroupInfo group = (GroupInfo) getGroup(groupPosition);
        //如果该条目不为空，则可操作
        if (group != null) {
            gholder.tv_group_name.setText(group.getName());
            //复选框点击监听
            gholder.cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //第一步是改变选中状态如果是选中的点击变为不选中，如果是不选中点击后变成选中
                    group.setChoosed(((CheckBox) v).isChecked());
                    checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());//暴露组选接口
                }
            });
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildHolder cholder;
        if (convertView == null) {
            cholder = new ChildHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopcart_product, null);
            cholder.cb_check = (CheckBox) convertView.findViewById(R.id.check_box);

            cholder.tv_product_desc = (TextView) convertView.findViewById(R.id.tv_intro);
            cholder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            cholder.iv_increase = (TextView) convertView.findViewById(R.id.tv_add);
            cholder.iv_decrease = (TextView) convertView.findViewById(R.id.tv_reduce);
            cholder.tv_count = (TextView) convertView.findViewById(R.id.tv_num);

            cholder.iv_adapter_list_pic = (ImageView) convertView.findViewById(R.id.iv_adapter_list_pic);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildHolder) convertView.getTag();
        }
        final ProductInfo productInfo = (ProductInfo) getChild(groupPosition, childPosition);
        //如果该条目不为空，则可操作
        if (productInfo != null) {
            cholder.tv_product_desc.setText(productInfo.getDesc());
            cholder.tv_price.setText("￥" + productInfo.getPrice() + "");
            cholder.tv_count.setText(productInfo.getCount() + "");
            cholder.cb_check.setChecked(productInfo.isChoosed());
            ImageLoader.getInstance().displayImage(productInfo.getImageUrl(), cholder.iv_adapter_list_pic,dd);

            //子条目多选框监听
            cholder.cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productInfo.setChoosed(((CheckBox) v).isChecked());
                    cholder.cb_check.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            //子条目增加监听
            cholder.iv_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露增加接口
                }
            });
            //子条目减少监听
            cholder.iv_decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露删减接口
                }
            });
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupHolder {
        CheckBox cb_check;
        TextView tv_group_name;
    }

    private class ChildHolder {
        CheckBox cb_check;
         ImageView iv_adapter_list_pic;
        TextView tv_product_name;
        TextView tv_product_desc;
        TextView tv_price;
        TextView iv_increase;
        TextView tv_count;
        TextView iv_decrease;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        public void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        public void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);
    }
}



