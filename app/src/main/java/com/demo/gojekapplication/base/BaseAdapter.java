package com.demo.gojekapplication.base;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter <T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private MutableLiveData<List<T>> data;
    private LifecycleOwner lifecycleOwner;

    @Override
    public int getItemCount() {
        return data == null || data.getValue() == null ? 0 : data.getValue().size();
    }

    public void setData(MutableLiveData<List<T>> data, LifecycleOwner lifecycleOwner) {
        this.data = data;
        this.lifecycleOwner = lifecycleOwner;
        this.data.observe(lifecycleOwner,listObserver);
    }

    public T getItem(int position) {
        return data == null || data.getValue() == null ? null : data.getValue().get(position);
    }

    protected MutableLiveData<List<T>> getData() {
        return data;
    }

    private Observer<List<T>> listObserver = this::onChanged;


    public LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    private void onChanged(List<T> ts) {
        notifyDataSetChanged();
    }
}
