package com.bwei.wangjianxun20171123.model.imodel;

import com.bwei.wangjianxun20171123.bean.ProductBean;
import com.bwei.wangjianxun20171123.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-11-23 14:07
 * 类的用途：详情页的Model
 */

public interface IProductModel {

    public void getProdectDetail(OnNetListener<ProductBean> onNetListener);
}
