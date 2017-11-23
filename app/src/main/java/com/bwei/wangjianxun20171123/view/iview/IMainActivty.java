package com.bwei.wangjianxun20171123.view.iview;

import com.bwei.wangjianxun20171123.bean.ProductBean;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-11-23 14:15
 * 类的用途：IMainActivty层
 */

public interface IMainActivty {
    //展示数据
    public void showProductDetail(ProductBean.DataBean dataBean);
    //跳转
    public void toGoodsCart();

}
