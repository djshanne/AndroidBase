package com.domain.usecase;

import android.util.Log;

import com.data.repository.characters.Repository;
import com.domain.error.ConnectionException;

import java.net.UnknownHostException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public abstract class Task<Response> extends Subscriber<Response> {
    private final UseCaseListener listener;

    protected boolean isExecuting = false;
    private Subscription subscription;
    private Observable<Response> call;
    private Repository.TYPE type;

    public void cancel() {
        Log.d(getTg(), "Canceling task --->" + getTg());
        subscription.unsubscribe();
    }

    public abstract boolean shouldDeliverCallback();

    public Task(UseCaseListener listener, final Repository.TYPE type, final int taskDelayedBy) {
        this.listener = listener;
        call = Observable.create(new Observable.OnSubscribe<Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    Log.d(getTg(), "Executing task --->" + getTg());
                    isExecuting = true;
                    Thread.sleep(taskDelayedBy);
                    Response r = prcsRqst(type);
                    if (shouldDeliverCallback()) {
                        subscriber.onNext(r);
                        subscriber.onCompleted();
                    }
                    isExecuting = false;
                    Log.d(getTg(), "Finished task --->" + getTg());
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                    Log.e(getTg(), ex.getMessage());
                    Log.e(getTg(), "Executing task Error--->" + getTg() + "--" + ex.getMessage());
                    isExecuting = false;
                    if (shouldDeliverCallback())
                        subscriber.onError(new ConnectionException());
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.e(getTg(), "Executing task Error--->" + getTg() + "--" + ex.getMessage());
                    if (shouldDeliverCallback())
                        subscriber.onError(ex);
                    isExecuting = false;
                    return;
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public void execute() {
        subscription = call.subscribe(this);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(Response response) {
        if (listener != null)
            listener.onSuccess(response);
    }

    @Override
    public void onError(Throwable e) {
        if (listener != null)
            listener.onFailure((Exception) e);

    }

    protected abstract Response prcsRqst(Repository.TYPE type) throws Exception;

    protected abstract String getTg();

    public Response executeSync(Repository.TYPE type) {
        try {
            return prcsRqst(type);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(getTg(), ex.getMessage());
        }
        return null;
    }

    public boolean isExecuting() {
        return isExecuting;
    }
}
