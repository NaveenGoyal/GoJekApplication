package com.demo.gojekapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import com.demo.gojekapplication.BR;

import dagger.android.support.DaggerFragment;

public abstract class BaseMVVMFragment<T extends BaseViewModel > extends DaggerFragment {

    public T viewModel;

    private ViewDataBinding viewDataBinding;


    /**
     * Instantiates a new Base mvvm fragment.
     */
    public BaseMVVMFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getContentView(inflater, container, savedInstanceState);

    }

    private View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        viewDataBinding.setLifecycleOwner(this);
        viewModel = createViewModel();

        if(viewModel != null){
            viewDataBinding.setVariable(BR.vm, viewModel);
        }

        return viewDataBinding.getRoot();
    }

    protected T getViewModel() {
        return viewModel;
    };

    protected abstract T createViewModel();

    public ViewDataBinding getDataBinding(){
        return viewDataBinding;
    }

    protected abstract int getLayout();

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
    }

    /**
     * Super class method, called when view is created
     * Load your view model here.
     *
     * @param view : inflated {@link View}
     */
    protected final void initLayout(View view) {
        onViewModelCreated(view, viewModel);
    }

    /**
     * Called after view model is created
     * @param view , View attached to this fragment
     * @param viewModel , view model attached to this fragment
     */
    public abstract void onViewModelCreated(View view, T viewModel);
}
