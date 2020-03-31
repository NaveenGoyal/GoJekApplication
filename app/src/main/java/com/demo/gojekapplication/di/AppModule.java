package com.demo.gojekapplication.di;

import android.app.Application;


import com.demo.gojekapplication.DemoApplication;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Provides the application context and includes other modules
 */
@Module(includes = {ActivityModule.class, FragmentModule.class, NetworkModule.class})
public abstract class AppModule {

    @Binds
    @Singleton
    abstract Application application(DemoApplication app);
}
