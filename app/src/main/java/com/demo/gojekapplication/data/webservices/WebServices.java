package com.demo.gojekapplication.data.webservices;

import com.demo.gojekapplication.data.model.Repository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface WebServices {

    @GET("repositories")
    Observable<List<Repository>> getRepositories(@Header("Content-Type") String content,
                                                 @Query("since") String since);
}
