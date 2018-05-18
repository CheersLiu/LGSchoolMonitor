package com.lancoo.lgschoolmonitor.base;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/17 11:22.
 */
public class ObjectLoader {
    protected  <T> Observable<T> observe(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io()).
                unsubscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }
}

