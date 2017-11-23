package com.bwei.wangjianxun20171123.view.iview;

import com.bwei.wangjianxun20171123.bean.GetCartsBean;

import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-11-23 15:38
 * 类的用途：IGoodsActivity层
 */

public interface IGoodsActivity {
    //展示数据
    public void showGoodsList(List<GetCartsBean.DataBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);
}
