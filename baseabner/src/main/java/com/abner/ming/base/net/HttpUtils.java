package com.abner.ming.base.net;


import android.content.Context;

import com.abner.ming.base.dialog.DialogLoading;
import com.abner.ming.base.model.Api;
import com.abner.ming.base.utils.CacheUtils;
import com.abner.ming.base.utils.NetworkUtils;
import com.abner.ming.base.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * author:AbnerMing
 * date:2019/4/18
 * 联网工具类
 */
public class HttpUtils {

    private static HttpUtils mHttpUtils = null;


    private HttpUtils() {
    }

    public synchronized static HttpUtils getHttpUtils() {
        synchronized (HttpUtils.class) {
            if (mHttpUtils == null) {
                mHttpUtils = new HttpUtils();
            }
        }
        return mHttpUtils;
    }

    private String base_url = Api.BASE_URL;
    private String url;


    //更改baseurl
    public HttpUtils setBaseUrl(String base_url) {
        this.base_url = base_url;
        return this;
    }

    //传递头参
    private Map<String, String> headMap = new HashMap<>();

    public HttpUtils setHead(Map<String, String> headMap) {
        this.headMap = headMap;
        return this;
    }

    //是否显示加载框
    private boolean isShowLoading;

    public HttpUtils isShowLoading(boolean isShowLoading) {
        this.isShowLoading = isShowLoading;
        return this;
    }

    //是否读取缓存
    private boolean isReadCache;

    public HttpUtils isReadCache(boolean isReadCache) {
        this.isReadCache = isReadCache;
        return this;
    }


    //传递上下文
    private Context mContext;

    public HttpUtils setContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }


    //get请求
    public HttpUtils get(String url, Map<String, String> map) {
        this.url = url;
        if (map == null) {
            map = new HashMap<>();
        }
        HttpService service = getHttpService();
        Observable<ResponseBody> ob = service.get(url, headMap, map);
        send(ob);
        return this;

    }


    //post请求
    public HttpUtils post(String url, Map<String, String> map) {
        this.url = url;
        HttpService service = getHttpService();
        Observable<ResponseBody> ob = service.post(url, headMap, map);
        send(ob);
        return this;
    }

    //put请求
    public HttpUtils put(String url, Map<String, String> map) {
        this.url = url;
        HttpService service = getHttpService();
        Observable<ResponseBody> ob = service.put(url, headMap, map);
        send(ob);
        return this;
    }

    //delete请求
    public HttpUtils delete(String url, Map<String, String> map) {
        this.url = url;
        HttpService service = getHttpService();
        Observable<ResponseBody> ob = service.delete(url, headMap, map);
        send(ob);
        return this;
    }


    //产生订阅
    private DialogLoading.Builder mLoading;

    private void send(Observable<ResponseBody> ob) {
        if (isShowLoading) {
            mLoading = new DialogLoading.Builder(mContext).alertBg();
            mLoading.show();
        }
        if (isReadCache && !NetworkUtils.isConnected(mContext)) {
            String json = CacheUtils.getCacheUtils().query(Utils.md5(url));
            successHttp(json);
            return;
        }

        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverIml<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            successHttp(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isShowLoading) {
                            mLoading.hint();
                        }
                        if (cls == null) {
                            mHttpListener.fail(e.getMessage());
                        } else {
                            mHttpBeanListener.fail(e.getMessage());
                        }

                    }
                });
    }

    private void successHttp(String json) {
        if (isReadCache && NetworkUtils.isConnected(mContext)) {
            CacheUtils.getCacheUtils().insert(Utils.md5(url), json);//缓存
        }
        if (isShowLoading) {
            mLoading.hint();
        }
        if (cls == null) {//返回字符串
            mHttpListener.success(json);
        } else {
            //返回JavaBean
            Object bean = new Gson().fromJson(json, cls);
            mHttpBeanListener.success(bean);
        }
    }


    //传递javabean接口
    private HttpBeanListener mHttpBeanListener;
    private Class cls;

    public void resultBean(Class cls, HttpBeanListener mHttpBeanListener) {
        this.cls = cls;
        this.mHttpBeanListener = mHttpBeanListener;
    }


    //返回JavaBean
    public interface HttpBeanListener<T> {
        void success(T t);

        void fail(String error);
    }


    //传递接口
    private HttpListener mHttpListener;

    public void result(HttpListener mHttpListener) {
        this.mHttpListener = mHttpListener;
    }

    //返回字符串
    public interface HttpListener {
        void success(String data);

        void fail(String error);
    }

    //获取请求接口
    private HttpService getHttpService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(base_url).build();
        return retrofit.create(HttpService.class);
    }

}
