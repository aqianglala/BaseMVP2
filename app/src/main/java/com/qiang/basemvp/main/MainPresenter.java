package com.qiang.basemvp.main;

import android.annotation.SuppressLint;

import com.qiang.basemvp.net.HttpHelper;
import com.qiang.library.app.BasePresenter;
import com.qiang.library.net.RxHelper;

import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.Presenter {

    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        HttpHelper.getRxRestService()
                .getData("https://www.baidu.com/")
                .compose(RxHelper.io_main())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getIView().showData(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getIView().showError();
                    }
                });
    }
}