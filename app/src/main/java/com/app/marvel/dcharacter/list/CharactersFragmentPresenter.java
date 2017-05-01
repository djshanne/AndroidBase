package com.app.marvel.dcharacter.list;

import com.app.marvel.base.ListFragmentController;
import com.app.marvel.base.LoadMoreItemsListener;
import com.app.marvel.base.PresenterList;
import com.data.repository.characters.Repository;
import com.data.service.request.Request;
import com.domain.usecase.GetCharactersUseCase;
import com.domain.usecase.UseCaseListener;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public class CharactersFragmentPresenter extends PresenterList implements LoadMoreItemsListener {


    private final ListFragmentController<BaseResponse<Data<Result>>> controller;
    private com.data.service.request.RequestList RequestList;
    private GetCharactersUseCase<BaseResponse<Data<Result>>> getCharactersUseCase;

    public CharactersFragmentPresenter(ListFragmentController<BaseResponse<Data<Result>>> controller) {
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
            public com.data.service.request.RequestList getRequest() {
                return RequestList;
            }

            @Override
            public boolean canOperateOnView() {
                return canOperateOnView;
            }
        };
    }

    @Override
    public void getData(Request request) {
        this.RequestList = (com.data.service.request.RequestList) request;
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
