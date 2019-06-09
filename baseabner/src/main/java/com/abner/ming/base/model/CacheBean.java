package com.abner.ming.base.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author:AbnerMing
 * date:2019/6/8
 */
@Entity
public class CacheBean {
    @Id(autoincrement = true)
    private Long id;

    private String key;

    private String value;

    @Generated(hash = 1523718252)
    public CacheBean(Long id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    @Generated(hash = 573552170)
    public CacheBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
