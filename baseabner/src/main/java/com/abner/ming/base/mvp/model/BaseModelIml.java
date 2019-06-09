package com.abner.ming.base.mvp.model;

import android.content.Context;

import com.abner.ming.base.net.HttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * author:AbnerMing
 * date:2019/4/18
 */
public class BaseModelIml implements BaseModel {


    private Class cls;

    public BaseModelIml(Class cls) {
        this.cls = cls;
    }

    //get请求
    @Override
    public void get(int type, String url, Map<String, String> map, CallBackListener listener) {
        doHttp(0, type, url, map, listener);
    }

    //post请求
    @Override
    public void post(int type, String url, Map<String, String> map, CallBackListener listener) {
        doHttp(1, type, url, map, listener);
    }

    //put请求
    @Override
    public void put(int type, String url, Map<String, String> map, CallBackListener listener) {
        doHttp(2, type, url, map, listener);
    }

    //delete请求
    @Override
    public void delete(int type, String url, Map<String, String> map, CallBackListener listener) {
        doHttp(3, type, url, map, listener);
    }

    //执行网络请求
    private void doHttp(int state, final int type, String url, Map<String, String> map, final CallBackListener listener) {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();

        httpUtils.setContext(mContext);

        httpUtils.isShowLoading(isShowLoading);//是否显示加载框

        httpUtils.isReadCache(isReadCache);

        httpUtils.setHead(headMap);//传递头参



        if (state == 0) {//get
            httpUtils.get(url, map);
        } else if (state == 1) {//post
            httpUtils.post(url, map);
        } else if (state == 2) {//put
            httpUtils.put(url, map);
        } else if (state == 3) {//delete
            httpUtils.delete(url, map);
        }

        httpUtils.result(new HttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                if (cls == null) {
                    listener.success(type, data);
                } else {
                    Object bean = new Gson().fromJson(data, cls);
                    listener.successBean(type, bean);
                }

            }

            @Override
            public void fail(String error) {
                listener.fail(type, error);
            }
        });

    }

    //是否显示加载框
    private boolean isShowLoading;
    public void isShowLoading(boolean isShowLoading){
        this.isShowLoading=isShowLoading;
    }
    //是否读取缓存
    private boolean isReadCache;

    public void isReadCache(boolean isReadCache) {
        this.isReadCache = isReadCache;
    }
    //传递头参
    private Map<String, String> headMap = new HashMap<>();

    public void setHead(Map<String, String> headMap) {
        this.headMap = headMap;
    }

    //传递上下文
    private Context mContext;
    public void setContext(Context mContext){
        this.mContext=mContext;
    }



}
