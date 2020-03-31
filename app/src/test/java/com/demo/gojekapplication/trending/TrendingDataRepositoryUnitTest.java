package com.demo.gojekapplication.trending;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.demo.gojekapplication.basetest.TestBaseClass;
import com.demo.gojekapplication.data.model.Repository;
import com.demo.gojekapplication.data.repository.TrendingDataRepository;
import com.demo.gojekapplication.data.repository.TrendingDataRepositoryImpl;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.List;

import io.reactivex.observers.TestObserver;
import kotlin.jvm.JvmField;
import static junit.framework.TestCase.assertNotNull;

public class TrendingDataRepositoryUnitTest extends TestBaseClass {

    private TrendingDataRepository trendingDataRepository = new TrendingDataRepositoryImpl();

    @Rule
    @JvmField
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Override
    public boolean hasMockServer() {
        return true;
    }

    @Test
    public final void getTrendingListSuccess() {
        mockHttpResponse("trending_list_success.json", HttpURLConnection.HTTP_OK);
        TestObserver<List<Repository>> testObserver = trendingDataRepository.getTrendingRepoList(webServices, "since", false).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors()
                .assertValue(trendingList -> {
                    assertNotNull("trending list is not null", trendingList);
                    TestCase.assertNotSame("list items is not zero", 0, trendingList.size());
                    return true;
                });

    }

    @Test
    public final void getTrendingListError() {
        mockHttpResponse("trending_list_success.json", HttpURLConnection.HTTP_BAD_GATEWAY);
        TestObserver<List<Repository>> testObserver = trendingDataRepository.getTrendingRepoList(webServices, "since", false).test();
        testObserver.awaitTerminalEvent();
        testObserver
                .assertError(e -> {
                    assertNotNull("Error not null",e);
                    return true;
                });
    }
}
