package com.demo.gojekapplication.data.repository;

import com.demo.gojekapplication.data.model.Repository;
import com.demo.gojekapplication.data.webservices.WebServices;

import java.util.List;

import io.reactivex.Observable;

public interface TrendingDataRepository {

    Observable<List<Repository>> getTrendingRepoList(WebServices webServices, String since, boolean cache);
 }
