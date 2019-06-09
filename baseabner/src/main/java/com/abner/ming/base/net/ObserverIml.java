package com.abner.ming.base.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author:AbnerMing
 * date:2019/4/18
 */
public abstract class ObserverIml<T> implements Observer<T>{
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T t) ;
    @Override
    public abstract void onError(Throwable e) ;

    @Override
    public void onComplete() {

    }
}
