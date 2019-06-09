package com.abner.ming.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.R;
import com.abner.ming.base.mvp.model.BaseModelIml;
import com.abner.ming.base.mvp.presenter.BasePresenterIml;
import com.abner.ming.base.mvp.view.BaseView;

import java.util.HashMap;
import java.util.Map;

/**
 * author:AbnerMing
 * date:2019/4/18
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements BaseView {
    private RelativeLayout titleLayout;
    private TextView baseTitle, titleRight;
    private ImageView baseBack;
    private BasePresenterIml basePresenter;


    /**
     * 设置标题
     */
    protected void setTitle(String title) {
        baseTitle.setText(title);
    }

    //是否显示返回键
    protected void isShowBack(boolean showBack) {
        if (showBack) {
            baseBack.setVisibility(View.VISIBLE);
        } else {
            baseBack.setVisibility(View.GONE);
        }
    }

    protected void setShowTitle(boolean isShow) {
        if (isShow) {
            titleLayout.setVisibility(View.GONE);
        } else {
            titleLayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        titleLayout = (RelativeLayout) findViewById(R.id.base_layout_title);
        baseTitle = (TextView) findViewById(R.id.base_title);
        baseBack = (ImageView) findViewById(R.id.base_view_back);
        titleRight = (TextView) findViewById(R.id.base_title_right);

        //创建用于添加子类传递的布局
        LinearLayout baseView = (LinearLayout) findViewById(R.id.base_view);
        //拿到子类布局
        View childView = View.inflate(this, getLayoutId(), null);

        baseView.addView(childView);

        initView();

        initData();


        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    //初始化View
    protected abstract void initData();

    //初始化View
    protected abstract void initView();

    //子类传递的一个layout
    public abstract int getLayoutId();

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //获取BasePresenterIml  null为获取字符串，获取JavaBean需要传JavaBean
    public BasePresenterIml getPresenter(Class cls) {
        BaseModelIml baseModel = new BaseModelIml(cls);
        baseModel.isShowLoading(isShowLoading);
        baseModel.isReadCache(isReadCache);
        baseModel.setContext(this);
        baseModel.setHead(headMap);
        basePresenter = new BasePresenterIml(baseModel, this);
        return basePresenter;
    }

    //isShowLoading 是否显示加载框  isReadCache 是否阅读缓存  获取String
    protected BasePresenterIml net(boolean isShowLoading, boolean isReadCache) {
        return doHttp(null, isShowLoading, isReadCache);
    }

    //获取JavaBean
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
            view = findViewById(id);
            sparseArray.put(id, view);
        }
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePresenter != null) {
            basePresenter.destory();
        }
    }
}
