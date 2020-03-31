package com.demo.gojekapplication.base;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class BaseViewModel extends ViewModel {

    private MutableLiveData<Boolean> isError = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoader = new MutableLiveData<>();
    private CompositeDisposable disposables = new CompositeDisposable();//Holds reference of all disposable
    private PublishSubject<Object> retrySubject = PublishSubject.create();

    /**
     * Show error view
     */
    public final void showError() {
        isError.postValue(true);
    }

    /**
     * Hides error view
     */
    public final void hideError() {
        isError.postValue(false);
    }

    /**
     * Shows progress_view bar
     */
    @MainThread
    public final void showLoader() {
        isLoader.postValue(true);
    }

    /**
     * Hides progress_view bar
     */
    @MainThread
    public final void hideLoader() {
        isLoader.postValue(false);
    }

    /**
     * Handle error from API
     */
    public void dispatchOnFailure() {
        hideLoader();
        showError();
        isError.postValue(true);
    }

    /**
     * Add disposable to composite disposable
     * @param disposable disposable returned from subscription
     */
    public void addDisposable(@NonNull Disposable disposable){
        disposables.add(disposable);
    }

    /**
     * Retry most recent API hit
     * @param observable {@link Observable} of type  {@link Throwable}
     * @return retry subject
     */
    public ObservableSource<?> getRetrySubject(@NonNull Observable<Throwable> observable) {
        return observable.zipWith(retrySubject, (o, o2) -> o);
    }

    /**
     * Call to retry API
     */
    public void retryNow(){
        hideError();
        showLoader();
        //initiate retry
        retrySubject.onNext(new Object());
    }

    /**
     * Called when view model is being destroyed
     *
     * Clear memory here
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        retrySubject = null;
        disposables.dispose();
    }

    public MutableLiveData<Boolean> getIsError() {
        return isError;
    }

    public MutableLiveData<Boolean> getIsLoader() {
        return isLoader;
    }
}
