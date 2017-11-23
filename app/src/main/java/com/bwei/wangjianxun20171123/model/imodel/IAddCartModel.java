package com.bwei.wangjianxun20171123.model.imodel;

import com.bwei.wangjianxun20171123.bean.AddCartBean;
import com.bwei.wangjianxun20171123.net.OnNetListener;

/**
 * 作者：王建勋
 * 时间：2017-11-23 14:50
 * 类的用途：
 */

public interface IAddCartModel {
    public void getAddCart(OnNetListener<AddCartBean> onNetListener);
}
