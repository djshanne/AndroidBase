package com.domain.usecase;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public interface UseCaseListener<R> {

    public void onSuccess(R t);

    public void onFailure(Exception error);

//    public void onNoConnectionError(Exception error);

}
