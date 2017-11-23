package com.bwei.wangjianxun20171123.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.wangjianxun20171123.R;
import com.bwei.wangjianxun20171123.bean.GetCartsBean;
import com.bwei.wangjianxun20171123.eventbus.MessageEvent;
import com.bwei.wangjianxun20171123.eventbus.PriceEvent;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-11-23 15:34
 * 类的用途：适配器类
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GetCartsBean.DataBean> groupList;
    private List<List<GetCartsBean.DataBean.ListBean>> childList;
    private final LayoutInflater inflater;

    public MyAdapter(Context context, List<GetCartsBean.DataBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //声明GroupViewHolder
        View view;
        final GroupViewHolder holder;
        if (convertView == null){
            //初始化GroupViewHolder
            holder = new GroupViewHolder();
            //使用布局加载器加载布局
            view = inflater.inflate(R.layout.item_parant,null);
            holder.cbGroup = (CheckBox) view.findViewById(R.id.cb_parent);
            holder.tv_number = (TextView) view.findViewById(R.id.tv_number);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        //赋值
        final GetCartsBean.DataBean dataBean = groupList.get(groupPosition);
        holder.cbGroup.setChecked(dataBean.isChecked());
        holder.tv_number.setText(dataBean.getSellerName());
        //一级checkbox的点击事件
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改变checkbox状态
                dataBean.setChecked(holder.cbGroup.isChecked());
                changeChildCbState(groupPosition, holder.cbGroup.isChecked());
                EventBus.getDefault().post(compute());
                changeAllCbState(isAllGroupCbSelected());
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //声明ChildViewHolder
        View view;
        final ChildViewHolder holder;
        if (convertView == null){
            //初始化ChildViewHolder
            holder = new ChildViewHolder();
            //使用布局加载器加载布局
            view = inflater.inflate(R.layout.item_child,null);
            holder.cbChild = (CheckBox) view.findViewById(R.id.cb_child);
            holder.iv_name = (ImageView) view.findViewById(R.id.iv_name);
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            holder.tv_pri = (TextView) view.findViewById(R.id.tv_pri);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        //赋值
        final GetCartsBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        holder.cbChild.setChecked(listBean.isChecked());
        holder.tv_content.setText(listBean.getTitle());
        holder.tv_pri.setText("优惠价：￥"+listBean.getPrice());
        //展示图片
        String[] strings = listBean.getImages().split("\\|");
        ImageLoader.getInstance().displayImage(strings[0],holder.iv_name);

        //二级checkbox
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置该条目对象里的checked属性值
                listBean.setChecked(holder.cbChild.isChecked());
                PriceEvent priceAndCountEvent = compute();
                EventBus.getDefault().post(priceAndCountEvent);

                if (holder.cbChild.isChecked()) {
                    //当前checkbox是选中状态
                    if (isAllChildCbSelected(groupPosition)) {
                        changGroupCbState(groupPosition, true);
                        changeAllCbState(isAllGroupCbSelected());
                    }
                } else {
                    changGroupCbState(groupPosition, false);
                    changeAllCbState(isAllGroupCbSelected());
                }
                //刷新列表
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //一级列表的VieHolder
    class GroupViewHolder {
        CheckBox cbGroup;
        TextView tv_number;
    }

    //二级列表的VieHolder
    class ChildViewHolder {
        CheckBox cbChild;
        ImageView iv_name;
        TextView tv_content;
        TextView tv_pri;
    }

    /**
     * 改变全选的状态
     *
     * @param flag
     */
    private void changeAllCbState(boolean flag) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setChecked(flag);
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 改变一级列表checkbox状态
     *
     * @param groupPosition
     */
    private void changGroupCbState(int groupPosition, boolean flag) {
        GetCartsBean.DataBean dataBean = groupList.get(groupPosition);
        dataBean.setChecked(flag);
    }

    /**
     * 改变二级列表checkbox状态
     *
     * @param groupPosition
     * @param flag
     */
    private void changeChildCbState(int groupPosition, boolean flag) {
        List<GetCartsBean.DataBean.ListBean> listBeen = childList.get(groupPosition);
        for (int i = 0; i < listBeen.size(); i++) {
            GetCartsBean.DataBean.ListBean listBean = listBeen.get(i);
            listBean.setChecked(flag);
        }
    }
    /**
     * 判断一级列表是否全部选中
     *
     * @return
     */
    private boolean isAllGroupCbSelected() {
        for (int i = 0; i < groupList.size(); i++) {
            GetCartsBean.DataBean dataBean = groupList.get(i);
            if (!dataBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二级列表是否全部选中
     *
     * @param groupPosition
     * @return
     */
    private boolean isAllChildCbSelected(int groupPosition) {
        List<GetCartsBean.DataBean.ListBean> listBeen = childList.get(groupPosition);
        for (int i = 0; i < listBeen.size(); i++) {
            GetCartsBean.DataBean.ListBean listBean = listBeen.get(i);
            if (!listBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算列表中，选中的钱和数量
     */
    private PriceEvent compute() {
        int price = 0;
        for (int i = 0; i < childList.size(); i++) {
            List<GetCartsBean.DataBean.ListBean> listBeen = childList.get(i);
            for (int j = 0; j < listBeen.size(); j++) {
                GetCartsBean.DataBean.ListBean listBean = listBeen.get(j);
                if (listBean.isChecked()) {
                    price += listBean.getNum() * listBean.getPrice();
                }
            }
        }
        PriceEvent priceAndCountEvent = new PriceEvent();
        priceAndCountEvent.setPrice(price);
        return priceAndCountEvent;
    }

    /**
     * 设置全选、反选
     *
     * @param flag
     */
    public void changeAllListCbState(boolean flag) {
        for (int i = 0; i < groupList.size(); i++) {
            changGroupCbState(i, flag);
            changeChildCbState(i, flag);
        }
        EventBus.getDefault().post(compute());
        notifyDataSetChanged();
    }

}
