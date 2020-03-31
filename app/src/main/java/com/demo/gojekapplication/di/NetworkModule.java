package com.demo.gojekapplication.di;

import android.app.Application;

import com.demo.gojekapplication.data.repository.TrendingDataRepository;
import com.demo.gojekapplication.data.repository.TrendingDataRepositoryImpl;
import com.demo.gojekapplication.data.webservices.WebServices;
import com.demo.gojekapplication.data.webservices.interceptor.ResponseInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static String BASE_URL = "https://github-trending-api.now.sh/";
    private static final int DEFAULT_TIME_OUT = 10;
    private static final int DEFAULT_WRITE_TIME_OUT = 10;
    private final static int CACHE_SIZE = 10 * 1024 * 1024; // 10 MB

    @Provides
    @Singleton
    WebServices providesWebServices(CallAdapter.Factory callFactory,
                                    Converter.Factory converterFactory,
                                    OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(callFactory)
                .addConverterFactory(converterFactory);
        return builder.build().create(WebServices.class);
    }

    @Provides
    @Singleton
    CallAdapter.Factory providesCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Converter.Factory providesConverterFactory() {

        Gson gson = new GsonBuilder().setLenient().create();

        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(Application context, Cache cache) {

//add cache to the client
        return new OkHttpClient.Builder() // share clients to have same cache and other resources
                .cache(cache)
                .addNetworkInterceptor(new ResponseInterceptor(context))
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)// retry on failure
                .build();

    }

    @Provides
    @Singleton
    Cache providesCache(Application context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");

        Cache cache = new Cache(httpCacheDirectory, CACHE_SIZE);
        return cache;
    }


    @Provides
    @Singleton
    TrendingDataRepository providesTrendingDataRepository() {
        return new TrendingDataRepositoryImpl();
    }
}
