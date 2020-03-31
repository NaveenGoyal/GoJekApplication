package com.demo.gojekapplication.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demo.gojekapplication.R;
import com.demo.gojekapplication.base.BaseMVVMFragment;
import com.demo.gojekapplication.databinding.FragmentListTrendingBinding;
import com.demo.gojekapplication.view.adapter.TrendingListAdapter;
import com.demo.gojekapplication.viewmodel.TrendingListViewModel;

import javax.inject.Inject;



public class TrendingListFragment extends BaseMVVMFragment<TrendingListViewModel> implements SwipeRefreshLayout.OnRefreshListener{

    @Inject
    TrendingListViewModel trendingListViewModel;

    private FragmentListTrendingBinding trendingListViewBinding;


    @Inject
    TrendingListAdapter trendingListAdapter;

    @Override
    protected TrendingListViewModel createViewModel() {
        return trendingListViewModel;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_list_trending;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    /**
     * Called after view model is created
     *
     * @param view      , View attached to this fragment
     * @param viewModel , view model attached to this fragment
     */
    @Override
    public void onViewModelCreated(View view, TrendingListViewModel viewModel) {
        trendingListViewBinding = (FragmentListTrendingBinding) getDataBinding();

        recyclerAndAdapterSetUp();
        setData();

    }

    private void setData() {
        trendingListViewModel.getTrendingListForRepsitory(false);
        trendingListViewModel.getTrendingList().observe(this, list-> showRecyclerView());
        trendingListAdapter.setTrendingListViewModel(trendingListViewModel);
        trendingListAdapter.setData(trendingListViewModel.getTrendingList(), this);
        trendingListViewBinding.swipeContainer.setRefreshing(false);
    }

    private void showRecyclerView() {
        if(trendingListViewBinding.rvTrendingList.getVisibility() != View.VISIBLE) {
            trendingListViewBinding.rvTrendingList.setVisibility(View.VISIBLE);
        } else {
            trendingListAdapter.notifyDataSetChanged();
        }
        trendingListViewBinding.swipeContainer.setOnRefreshListener(this);
    }

    private void recyclerAndAdapterSetUp() {
        trendingListViewBinding.rvTrendingList.setLayoutManager(new LinearLayoutManager(getActivity()));
        trendingListViewBinding.rvTrendingList.setAdapter(trendingListAdapter);
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        trendingListViewModel.getTrendingListForRepsitory(true);
        trendingListViewModel.getTrendingList().observe(this, list-> onRefreshResponse());
    }

    private void onRefreshResponse() {
        trendingListViewBinding.swipeContainer.setRefreshing(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.trending_menu_items, menu);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_by_name:
                trendingListViewModel.sortByName();
                return true;
            case R.id.action_sort_by_stars:
                trendingListViewModel.sortByStars();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
