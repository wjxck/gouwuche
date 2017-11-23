package com.bwei.wangjianxun20171123.presenter;

import com.bwei.wangjianxun20171123.bean.ProductBean;
import com.bwei.wangjianxun20171123.model.ProductModel;
import com.bwei.wangjianxun20171123.model.imodel.IProductModel;
import com.bwei.wangjianxun20171123.net.OnNetListener;
import com.bwei.wangjianxun20171123.view.iview.IMainActivty;

/**
 * 作者：王建勋
 * 时间：2017-11-23 14:14
 * 类的用途：详情页的Presenter层
 */

public class ProductPresenter {

    private final IProductModel iProductModel;
    private final IMainActivty iMainActivty;

    public ProductPresenter(IMainActivty iMainActivty) {
        iProductModel = new ProductModel();
        this.iMainActivty = iMainActivty;
    }

    public void getProdectDetail(){
        //调用Model层的方法
        iProductModel.getProdectDetail(new OnNetListener<ProductBean>() {
            @Override
            public void onSuccess(ProductBean productBean) {
                //展示数据
                iMainActivty.showProductDetail(productBean.getData());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
