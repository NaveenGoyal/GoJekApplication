package com.demo.gojekapplication.data.webservices.interceptor;

import android.content.Context;

import com.demo.gojekapplication.data.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ResponseInterceptor implements Interceptor {
    private Context context;

    public ResponseInterceptor(Context context) {
        this.context = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        if (Utils.isNetworkEnabled(context)) {
            int maxAge = 7200; // read from cache for 2 hours
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 0; // no stale data needed
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }

    }
}
