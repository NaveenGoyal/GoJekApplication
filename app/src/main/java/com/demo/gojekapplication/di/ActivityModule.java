package com.demo.gojekapplication.di;



import com.demo.gojekapplication.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Add all new activities here to enable injection
 */
@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivityInjector();


}
