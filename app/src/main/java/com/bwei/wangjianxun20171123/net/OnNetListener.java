package com.bwei.wangjianxun20171123.net;

/**
 * 作者：王建勋
 * 时间：2017-11-23 13:49
 * 类的用途：接口回调
 */

public interface OnNetListener<T> {
    //成功的接口回调
    public void onSuccess(T t);
    //失败的接口回调
    public void onFailure(Exception e);
}
