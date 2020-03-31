package com.demo.gojekapplication.trending;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.demo.gojekapplication.basetest.TestBaseClass;
import com.demo.gojekapplication.data.model.Repository;
import com.demo.gojekapplication.data.repository.TrendingDataRepositoryImpl;
import com.demo.gojekapplication.data.webservices.WebServices;
import com.demo.gojekapplication.datatest.MockRepositoryModel;
import com.demo.gojekapplication.viewmodel.TrendingListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import io.reactivex.Observable;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@Config(manifest= Config.NONE)
public class TrendingListViewModelUnitTest extends TestBaseClass {

    @Mock
    private Observer<List<Repository>> repoListObserver;

    private TrendingListViewModel trendingListViewModel;

    @Mock
    private TrendingDataRepositoryImpl trendingDataRepository;

    @Mock
    private WebServices webServices;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Override
    public boolean hasMockServer() {
        return true;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        trendingListViewModel = new TrendingListViewModel(trendingDataRepository, webServices);
        trendingListViewModel.getTrendingList().observeForever(repoListObserver);
    }

    @Test
    public void trendingListSuccess() {
        mockTrendingRepoListSuccessResponse();
        trendingListViewModel.getTrendingListForRepsitory(false);
        verify(repoListObserver).onChanged(any());
        assertFalse("When success loader value should be false",trendingListViewModel.getIsLoader().getValue());
        assertFalse("When success Error value should be false",trendingListViewModel.getIsError().getValue());
        assertNotNull("trending repo list should not be null",trendingListViewModel.getTrendingList());
        assertTrue("trending repo list not be empty",trendingListViewModel.getTrendingList().getValue().size() > 0);
        assertNotNull("trending repo list repo Author should not be null",trendingListViewModel.getTrendingList().getValue().get(0).getAuthor());
        assertNotNull("trending repo list repo Name should not be null",trendingListViewModel.getTrendingList().getValue().get(0).getName());

    }

    @Test
    public void testNull() {
        Mockito.when(trendingDataRepository.getTrendingRepoList(webServices,"since", false)).thenReturn(null);
        assertNotNull("Trending live data should not be null",trendingListViewModel.getTrendingList());

        assertTrue("Trending live data should not be null",trendingListViewModel.getTrendingList().hasObservers());
    }

    @Test
    public void trendingListError() {
        mockTrendingRepoListErrorResponse();
        assertNull("Loader value should be false",trendingListViewModel.getIsLoader().getValue());
        assertNull("Error value should be true",trendingListViewModel.getIsError().getValue());
    }

    private void mockTrendingRepoListSuccessResponse() {
        Mockito.when(trendingDataRepository.getTrendingRepoList(webServices, "since", true))
                .thenReturn(Observable.just(MockRepositoryModel.mockTrendingList()));
    }

    private void mockTrendingRepoListErrorResponse() {
        Mockito.when(trendingDataRepository.getTrendingRepoList(webServices, "since", false))
                .thenReturn(Observable.error(new Throwable("Response Error")));
    }


}
