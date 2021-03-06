package com.demo.gojekapplication.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.demo.gojekapplication.BR;
import com.demo.gojekapplication.R;
import com.demo.gojekapplication.base.BaseAdapter;
import com.demo.gojekapplication.base.BaseViewHolder;
import com.demo.gojekapplication.data.model.Repository;
import com.demo.gojekapplication.viewmodel.TrendingListViewModel;

import javax.inject.Inject;

public class TrendingListAdapter extends BaseAdapter<Repository, BaseViewHolder> implements View.OnClickListener {

    public TrendingListViewModel trendingListViewModel;


    @Inject
    public TrendingListAdapter() {
    }
    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BaseViewHolder.createHolder(parent, R.layout.trending_list_item);
    }

    public TrendingListViewModel getTrendingListViewModel() {
        return trendingListViewModel;
    }

    public void setTrendingListViewModel(TrendingListViewModel trendingListViewModel) {
        this.trendingListViewModel = trendingListViewModel;
    }


    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Repository item = getItem(position);
        holder.getBinding().getRoot().setOnClickListener(this);
        holder.getBinding().setVariable(BR.click_listener, this);
        holder.getBinding().setVariable(BR.model, trendingListViewModel);
        holder.bind(item);
    }

    @Override
    public void onClick(View view) {
    }
}
