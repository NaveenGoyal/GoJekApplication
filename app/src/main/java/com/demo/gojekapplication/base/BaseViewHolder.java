package com.demo.gojekapplication.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.gojekapplication.BR;


public class BaseViewHolder<T>  extends RecyclerView.ViewHolder {

    private ViewDataBinding dataBinding;

    public BaseViewHolder(ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.dataBinding = dataBinding;
    }


    public ViewDataBinding getBinding(){
        return dataBinding;
    }

    /**
     * Binds the adapter data to view
     * @param data , Item data for this view
     */
    public void bind(T data) {
        //Make sure you define a variable in xml named holder.
        dataBinding.setVariable(BR.holder, data);
        dataBinding.executePendingBindings();
    }

    /**
     * Creates the binding for provided layout
     *
     * @param parent , Parent view
     * @param layout , Layout resource id
     * @return ViewHolder object with data binding
     */
    public static BaseViewHolder createHolder(ViewGroup parent, int layout) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                layout, parent, false);
        return new BaseViewHolder(binding);
    }

}
