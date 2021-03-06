package com.qiang.library.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements IView {

    protected P mPresenter;

    protected abstract Object setLayout();

    protected abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    protected abstract P loadPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        ButterKnife.bind(this, rootView);
        mPresenter = loadPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        onBindView(savedInstanceState, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
