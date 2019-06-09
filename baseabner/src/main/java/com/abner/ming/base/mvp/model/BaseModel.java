package com.abner.ming.base.mvp.model;

import java.util.Map;

/**
 * author:AbnerMing
 * date:2019/4/18
 */
public interface BaseModel {

    interface CallBackListener<T> {
        //请求成功
        void success(int type, String data);

        //请求成功，返回JavaBean

        void successBean(int type, T t);

        //请求失败
        void fail(int type, String error);
    }

    void get(int type, String url, Map<String, String> map, CallBackListener listener);

    void post(int type, String url, Map<String, String> map, CallBackListener listener);

    void put(int type, String url, Map<String, String> map, CallBackListener listener);

    void delete(int type, String url, Map<String, String> map, CallBackListener listener);

}
