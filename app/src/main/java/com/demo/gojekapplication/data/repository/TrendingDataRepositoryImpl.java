package com.demo.gojekapplication.data.repository;

import com.demo.gojekapplication.data.model.Repository;
import com.demo.gojekapplication.data.webservices.WebServices;

import java.util.List;

import io.reactivex.Observable;

public class TrendingDataRepositoryImpl implements TrendingDataRepository {
    private static String CONTENT_TYPE = "application/json";
    private static String BASE_URL = "https://github-trending-api.now.sh/";

    @Override
    public Observable<List<Repository>> getTrendingRepoList(WebServices webServices, String since, boolean cache) {
        return webServices.getRepositories(CONTENT_TYPE, since);
    }
}
