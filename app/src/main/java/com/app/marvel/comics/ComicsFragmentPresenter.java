package com.app.marvel.comics;

import com.app.marvel.base.ListFragmentController;
import com.app.marvel.base.LoadMoreItemsListener;
import com.app.marvel.base.PresenterList;
import com.data.repository.characters.Repository;
import com.data.service.request.Request;
import com.data.service.request.RequestList;
import com.domain.usecase.GetComicsUseCase;
import com.domain.usecase.UseCaseListener;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Comics;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public class ComicsFragmentPresenter extends PresenterList implements LoadMoreItemsListener {


    private final ListFragmentController<BaseResponse<Data<Comics>>> controller;
    private RequestList RequestComics;
    private GetComicsUseCase<BaseResponse<Data<Comics>>> getCharactersUseCase;

    public ComicsFragmentPresenter(ListFragmentController<BaseResponse<Data<Comics>>> controller) {
        super(controller);
        this.controller = controller;
        initGetCharactersUseCase();
    }

    private void initGetCharactersUseCase() {
        getCharactersUseCase = new GetComicsUseCase<BaseResponse<Data<Comics>>>(new UseCaseListener<BaseResponse<Data<Comics>>>() {
            @Override
            public void onSuccess(BaseResponse<Data<Comics>> t) {
                controller.setData(t);
                handleOnSuccess(controller, !t.getData().getResults().isEmpty());
            }

            @Override
            public void onFailure(Exception error) {
                handleOnError(controller);
            }
        }) {
            @Override
            public RequestList getRequest() {
                return RequestComics;
            }

            @Override
            public boolean canOperateOnView() {
                return canOperateOnView;
            }
        };
    }

    @Override
    public void getData(Request request) {
        this.RequestComics = (RequestList) request;
        controller.showProgressBar(false);
        getCharactersUseCase.execute(Repository.TYPE.CLOUD);
    }


    public LoadMoreItemsListener getLoadMoreListener() {
        return this;
    }

    @Override
    public void onLoadMore() {
        if (hasMoreItems) {
            controller.showProgressBar(false);
            getCharactersUseCase.execute(Repository.TYPE.CLOUD);
        }
    }

}
