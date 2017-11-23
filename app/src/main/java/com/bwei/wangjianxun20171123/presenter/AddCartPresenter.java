package com.bwei.wangjianxun20171123.presenter;

import com.bwei.wangjianxun20171123.bean.AddCartBean;
import com.bwei.wangjianxun20171123.model.AddCartModel;
import com.bwei.wangjianxun20171123.model.imodel.IAddCartModel;
import com.bwei.wangjianxun20171123.net.OnNetListener;
import com.bwei.wangjianxun20171123.view.iview.IMainActivty;

/**
 * 作者：王建勋
 * 时间：2017-11-23 14:51
 * 类的用途：添加到购物车Presenter层
 */

public class AddCartPresenter {

    private final IAddCartModel iAddCartModel;
    private final IMainActivty iMainActivty;

    public AddCartPresenter(IMainActivty iMainActivty) {
        iAddCartModel = new AddCartModel();
        this.iMainActivty = iMainActivty;
    }

    public void getAddCart(){
        iAddCartModel.getAddCart(new OnNetListener<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                iMainActivty.toGoodsCart();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
