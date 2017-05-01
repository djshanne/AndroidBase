package com.app.marvel.info;

import com.app.marvel.base.ListFragmentController;
import com.app.marvel.base.LoadMoreItemsListener;
import com.app.marvel.base.PresenterList;
import com.data.repository.characters.Repository;
import com.data.service.request.Request;
import com.data.service.request.RequestCharacters;
import com.domain.usecase.GetCharactersUseCase;
import com.domain.usecase.UseCaseListener;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public class InfoFragmentPresenter extends PresenterList implements LoadMoreItemsListener {


    private final ListFragmentController<BaseResponse<Data<Result>>> controller;
    private RequestCharacters requestCharacters;
    private GetCharactersUseCase<BaseResponse<Data<Result>>> getCharactersUseCase;

    public InfoFragmentPresenter(ListFragmentController<BaseResponse<Data<Result>>> controller) {
        super(controller);
        this.controller = controller;
        initGetCharactersUseCase();
    }

    private void initGetCharactersUseCase() {
        getCharactersUseCase = new GetCharactersUseCase<BaseResponse<Data<Result>>>(new UseCaseListener<BaseResponse<Data<Result>>>() {
            @Override
            public void onSuccess(BaseResponse<Data<Result>> t) {
                controller.setData(t);
                handleOnSuccess(controller, !t.getData().getResults().isEmpty());
            }

            @Override
            public void onFailure(Exception error) {
                handleOnError(controller);
            }
        }) {
            @Override
            public RequestCharacters getRequest() {
                return requestCharacters;
            }

            @Override
            public boolean canOperateOnView() {
                return canOperateOnView;
            }
        };
    }

    @Override
    public void getData(Request request) {
        this.requestCharacters = (RequestCharacters) request;
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
