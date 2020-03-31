package com.demo.gojekapplication.viewmodel;

import androidx.annotation.MainThread;
import androidx.lifecycle.MutableLiveData;

import com.demo.gojekapplication.base.BaseViewModel;
import com.demo.gojekapplication.data.model.Repository;
import com.demo.gojekapplication.data.repository.TrendingDataRepository;
import com.demo.gojekapplication.data.webservices.WebServices;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class TrendingListViewModel extends BaseViewModel {

    public TrendingDataRepository trendingDataRepository;
    private WebServices webServices;

    private MutableLiveData<List<Repository>> trendingList = new MutableLiveData<>();

    @Inject
    public TrendingListViewModel(TrendingDataRepository trendingDataRepository, WebServices webServices) {
        this.trendingDataRepository = trendingDataRepository;
        this.webServices = webServices;
    }

    @MainThread
    public void getTrendingListForRepsitory(boolean isRefresh) {
        hideError();
        if(!isRefresh)
            showLoader();
        addDisposable(trendingDataRepository.getTrendingRepoList(webServices, "since", true)
                .map(result -> result != null ? result : null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError(t -> dispatchOnFailure())
                //Retry logic to call api again
                .retryWhen(this::getRetrySubject)
                .subscribe(result ->{
                    hideLoader();
                    hideError();
                    trendingList.postValue(result);
                },error -> {dispatchOnFailure();}));

    }

    public MutableLiveData<List<Repository>> getTrendingList() {
        return trendingList;
    }

    public void sortByName() {
        List<Repository> repositories = trendingList.getValue();
        if(repositories != null) {
            Collections.sort(repositories, (repository, t1) -> repository.getAuthor().toLowerCase().compareTo(t1.getAuthor().toLowerCase()));

            trendingList.postValue(repositories);
        }
    }

    public void sortByStars() {
        List<Repository> repositories = trendingList.getValue();

        if(repositories != null) {
            Collections.sort(repositories, (repository, t1) -> t1.getStars().compareTo(repository.getStars()));

            trendingList.postValue(repositories);
        }
    }

    public void itemClicked(Repository holder) {
        List<Repository> temp = trendingList.getValue();
        for(Repository repository : temp) {
            if(repository.isSelected()) {
                repository.setSelected(false);
                break;
            }
        }
        holder.setSelected(true);
        trendingList.postValue(temp);
    }

}
