package com.abner.ming.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abner.ming.base.mvp.model.BaseModelIml;
import com.abner.ming.base.mvp.presenter.BasePresenterIml;
import com.abner.ming.base.mvp.view.BaseView;

import java.util.HashMap;
import java.util.Map;

/**
 * author:AbnerMing
 * date:2019/6/7
 */
public abstract class BaseFragment extends Fragment implements BaseView{
    private View viewLayout;
    private BasePresenterIml basePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLayout=View.inflate(getActivity(),getLayoutId(),null);
        initView(viewLayout);
        return viewLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //初始化View
    protected abstract void initData();

    //初始化View
    protected abstract void initView(View view);

    //子类传递的一个layout
    public abstract int getLayoutId();

    protected void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    //获取BasePresenterIml  null为获取字符串，获取JavaBean需要传JavaBean
    public BasePresenterIml getPresenter(Class cls) {
        BaseModelIml baseModel = new BaseModelIml(cls);
        baseModel.isShowLoading(isShowLoading);
        baseModel.isReadCache(isReadCache);
        baseModel.setContext(getActivity());
        baseModel.setHead(headMap);
        basePresenter = new BasePresenterIml(baseModel, this);
        return basePresenter;
    }

    protected BasePresenterIml net(boolean isShowLoading, boolean isReadCache) {
        return doHttp(null, isShowLoading, isReadCache);
    }

    protected BasePresenterIml net(boolean isShowLoading, boolean isReadCache, Class cls) {
        return doHttp(cls, isShowLoading, isReadCache);
    }

    protected BasePresenterIml doHttp(Class cls, boolean isShowLoading, boolean isReadCache) {
        isShowLoading(isShowLoading);
        isReadCache(isReadCache);
        return getPresenter(cls);
    }

    //是否读取缓存
    private boolean isReadCache;

    public void isReadCache(boolean isReadCache) {
        this.isReadCache = isReadCache;
    }

    //是否显示加载框
    private boolean isShowLoading;

    public void isShowLoading(boolean isShowLoading) {
        this.isShowLoading = isShowLoading;
    }

    private Map<String, String> headMap = new HashMap<>();

    public void setHead(Map<String, String> headMap) {
        this.headMap = headMap;
    }

    @Override
    public void success(int type, String data) {

    }

    @Override
    public void successBean(int type, Object o) {

    }

    @Override
    public void fail(int type, String error) {

    }

    private SparseArray<View> sparseArray = new SparseArray<>();

    //用于获取控件的方法
    public View get(int id) {
        View view = sparseArray.get(id);
        if (view == null) {
            view = viewLayout.findViewById(id);
            sparseArray.put(id, view);
        }
        return view;
    }

}
