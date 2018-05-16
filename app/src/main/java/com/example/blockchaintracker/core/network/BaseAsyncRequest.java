package com.example.blockchaintracker.core.network;

import android.content.Context;
import android.util.Log;

import com.example.blockchaintracker.BlockchainTrackerApplication;
import com.example.blockchaintracker.api.BlockchainTrackerApi;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseAsyncRequest<T, Params> {

    private final Scheduler threadExecutor = Schedulers.newThread();
    private final Scheduler postExecutionThread = AndroidSchedulers.mainThread();
    private final CompositeDisposable disposables;

    private Context context;

    public BaseAsyncRequest(Context context) {
        this.context = context;
        this.disposables = new CompositeDisposable();
    }

    public abstract Observable<T> buildObservableRequest(Params params);

    public void execute(DisposableObserver<T> observer, Params params) {
        final Observable<T> observable = this.buildObservableRequest(params)
                .doOnNext(rs -> {

                })
                .doOnError(throwable -> {
                    //todo handle errors
                })
                .subscribeOn(threadExecutor)
                .observeOn(postExecutionThread);
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public BlockchainTrackerApi getApi() {
        return ((BlockchainTrackerApplication) context.getApplicationContext()).getRestApi();
    }
}