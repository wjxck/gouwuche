package com.bwei.wangjianxun20171123.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.wangjianxun20171123.R;
import com.bwei.wangjianxun20171123.adapter.MyAdapter;
import com.bwei.wangjianxun20171123.bean.GetCartsBean;
import com.bwei.wangjianxun20171123.eventbus.MessageEvent;
import com.bwei.wangjianxun20171123.eventbus.PriceEvent;
import com.bwei.wangjianxun20171123.presenter.GetCartPresenter;
import com.bwei.wangjianxun20171123.view.iview.IGoodsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class GoodsActivity extends AppCompatActivity implements IGoodsActivity {

    private ExpandableListView mElv;
    private CheckBox mCheckbox2;
    /**
     * 总价：￥999.99
     */
    private TextView mTvPrice;
    private MyAdapter adapter;
    private ImageView mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        //关联Presenter层
        GetCartPresenter getCartPresenter = new GetCartPresenter(this);
        //接收消息
        EventBus.getDefault().register(this);
        initView();
        getCartPresenter.getGetCarts();
        //设置全选框的点击事件
        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //初始化组件
    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) findViewById(R.id.checkbox2);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceEvent event) {
        mTvPrice.setText("总价：￥" + event.getPrice());
    }

    @Override
    public void showGoodsList(List<GetCartsBean.DataBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        adapter = new MyAdapter(this, groupList, childList);
        mElv.setAdapter(adapter);
        //去掉一级列表中的箭头
        mElv.setGroupIndicator(null);
        //默认让其全部展开
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
    }
}
