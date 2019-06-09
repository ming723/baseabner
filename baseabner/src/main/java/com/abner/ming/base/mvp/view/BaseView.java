package com.abner.ming.base.mvp.view;

/**
 * author:AbnerMing
 * date:2019/4/18
 */
public interface BaseView<T> {

    //请求成功
    void success(int type, String data);

    void successBean(int type, T t);

    //请求失败
    void fail(int type, String error);
}
