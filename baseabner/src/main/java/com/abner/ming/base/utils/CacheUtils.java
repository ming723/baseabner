package com.abner.ming.base.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.abner.ming.base.greendao.CacheBeanDao;
import com.abner.ming.base.greendao.DaoMaster;
import com.abner.ming.base.model.CacheBean;
import com.abner.ming.base.net.HttpUtils;

import java.util.List;

/**
 * author:AbnerMing
 * date:2019/6/8
 */
public class CacheUtils {
    private static CacheUtils mCacheUtils = null;
    private CacheBeanDao dao;

    private CacheUtils() {
    }

    public synchronized static CacheUtils getCacheUtils() {
        synchronized (CacheUtils.class) {
            if (mCacheUtils == null) {
                mCacheUtils = new CacheUtils();
            }
        }
        return mCacheUtils;
    }

    //初始化
    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "cache");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        dao = mDaoMaster.newSession().getCacheBeanDao();
    }

    //添加
    public void insert(String key, String value) {
        List<CacheBean> list = dao.loadAll();
        boolean isHave = false;
        for (CacheBean b : list) {
            String k = b.getKey();
            if (k.equals(key)) {
                isHave = true;
                b.setValue(value);
                dao.update(b);
            }
        }
        if (!isHave) {
            CacheBean cacheBean = new CacheBean();
            cacheBean.setKey(key);
            cacheBean.setValue(value);
            dao.insert(cacheBean);
        }
    }

    //查询
    public String query(String key) {
        List<CacheBean> list = dao.loadAll();
        String value=null;
        for (CacheBean b : list) {
            String k = b.getKey();
            if (k.equals(key)) {
                value = b.getValue();
            }
        }
        return value;
    }
}
