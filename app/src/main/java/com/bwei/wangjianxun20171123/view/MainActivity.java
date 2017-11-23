package com.bwei.wangjianxun20171123.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwei.wangjianxun20171123.R;
import com.bwei.wangjianxun20171123.bean.ProductBean;
import com.bwei.wangjianxun20171123.presenter.AddCartPresenter;
import com.bwei.wangjianxun20171123.presenter.ProductPresenter;
import com.bwei.wangjianxun20171123.view.iview.IMainActivty;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity implements IMainActivty, View.OnClickListener {

    private ImageView mIvXq;
    /**
     * 锤子
     */
    private TextView mTvTitle;
    /**
     * 原价：￥100.00
     */
    private TextView mTvPrice;
    /**
     * 优惠价：￥100.00
     */
    private TextView mTvPrice1;
    /**
     * 购物车
     */
    private RadioButton mRbGoodcart;
    /**
     * 加入购物车
     */
    private RadioButton mRbAddcart;
    private ProductPresenter productPresenter;
    private AddCartPresenter addCartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //关联Presenter层
        productPresenter = new ProductPresenter(this);
        addCartPresenter = new AddCartPresenter(this);
        //初始化组件
        initView();
        productPresenter.getProdectDetail();
        addCartPresenter.getAddCart();
    }

    //初始化组件
    private void initView() {
        mIvXq = (ImageView) findViewById(R.id.iv_xq);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvPrice1 = (TextView) findViewById(R.id.tv_price1);
        mRbGoodcart = (RadioButton) findViewById(R.id.rb_goodcart);
        mRbGoodcart.setOnClickListener(this);
        mRbAddcart = (RadioButton) findViewById(R.id.rb_addcart);
        mRbAddcart.setOnClickListener(this);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_goodcart:
                //跳转到GoodsActivity
                Intent intent = new Intent(MainActivity.this,GoodsActivity.class);
                startActivity(intent);
                break;
            case R.id.rb_addcart:
                addCartPresenter.getAddCart();
                break;
        }
    }

    @Override
    public void showProductDetail(ProductBean.DataBean dataBean) {
        //为展示的赋值
        mTvTitle.setText(dataBean.getTitle());
        mTvPrice1.setText("优惠价:"+dataBean.getPrice());
        String[] strings = dataBean.getImages().split("\\|");
        ImageLoader.getInstance().displayImage(strings[0],mIvXq);
    }

    @Override
    public void toGoodsCart() {
        //跳转到GoodsActivity
        Intent intent = new Intent(MainActivity.this,GoodsActivity.class);
        startActivity(intent);
    }

}
