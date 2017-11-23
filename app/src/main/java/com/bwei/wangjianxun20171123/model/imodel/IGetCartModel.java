package com.bwei.wangjianxun20171123.model.imodel;

import com.bwei.wangjianxun20171123.bean.GetCartsBean;
import com.bwei.wangjianxun20171123.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-11-23 15:01
 * 类的用途：购物车的Model层
 */

public interface IGetCartModel {
    public void getGetCart(OnNetListener<GetCartsBean> onNetListener);
}
