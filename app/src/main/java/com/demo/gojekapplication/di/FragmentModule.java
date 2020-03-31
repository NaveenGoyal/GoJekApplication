package com.demo.gojekapplication.di;

import com.demo.gojekapplication.view.fragment.TrendingListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Add new fragments here to support injection
 */
@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract TrendingListFragment bindTrendingListFragment();
}