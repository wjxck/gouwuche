package com.bwei.wangjianxun20171123.presenter;

import com.bwei.wangjianxun20171123.bean.AddCartBean;
import com.bwei.wangjianxun20171123.bean.GetCartsBean;
import com.bwei.wangjianxun20171123.model.AddCartModel;
import com.bwei.wangjianxun20171123.model.GetCartModel;
import com.bwei.wangjianxun20171123.model.imodel.IAddCartModel;
import com.bwei.wangjianxun20171123.model.imodel.IGetCartModel;
import com.bwei.wangjianxun20171123.net.OnNetListener;
import com.bwei.wangjianxun20171123.view.iview.IGoodsActivity;
import com.bwei.wangjianxun20171123.view.iview.IMainActivty;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王建勋
 * 时间：2017-11-23 15:01
 * 类的用途：购物车的Presenter层
 */

public class GetCartPresenter {
    private final IGetCartModel iGetCartModel;
    private final IGoodsActivity iGoodsActivity;

    public GetCartPresenter(IGoodsActivity iGoodsActivity) {
        iGetCartModel = new GetCartModel();
        this.iGoodsActivity = iGoodsActivity;
    }

    public void getGetCarts() {
        //调用Model层的方法
        iGetCartModel.getGetCart(new OnNetListener<GetCartsBean>() {
            @Override
            public void onSuccess(GetCartsBean getCartsBean) {
                //获取数据
                List<GetCartsBean.DataBean> dataBeen = getCartsBean.getData();
                List<List<GetCartsBean.DataBean.ListBean>> childList = new ArrayList<List<GetCartsBean.DataBean.ListBean>>();
                //遍历添加数据
                for (int i = 0; i < dataBeen.size(); i++) {
                    List<GetCartsBean.DataBean.ListBean> listBeen = dataBeen.get(i).getList();
                    childList.add(listBeen);
                }
                //展示数据
                iGoodsActivity.showGoodsList(dataBeen,childList);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
