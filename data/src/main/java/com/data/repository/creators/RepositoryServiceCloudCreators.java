package com.data.repository.creators;

import com.data.repository.RepositoryServiceCloudBase;
import com.data.service.Cloud;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;
import com.model.bean.characters.comics.Creatorss;

import retrofit2.Response;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public class RepositoryServiceCloudCreators extends RepositoryServiceCloudBase implements RepositoryCreators {

    public RepositoryServiceCloudCreators() {
    }

    @Override
    public BaseResponse<Data<Creatorss>> getCreators(RequestList requestCharacters) throws Exception {
        Response<BaseResponse<Data<Creatorss>>> r = Cloud.getApiApp().getCreators(getApiKey(), getHash(), getTs(), requestCharacters.getPage()).execute();
        switch (r.code()) {
            default:
                return (BaseResponse<Data<Creatorss>>) handleResponse(r);
        }
    }

    @Override
    public BaseResponse<Data<Creatorss>> getCreator(RequestList RequestList) throws Exception {
        Response<BaseResponse<Data<Result>>> r;
        r = Cloud.getApiApp().getCreator(RequestList.getId(), getApiKey(), getHash(), getTs(), RequestList.getPage()).execute();
        switch (r.code()) {
            default:
                return (BaseResponse<Data<Creatorss>>) handleResponse(r);
        }
    }


    @Override
    public void setCreators(BaseResponse<Data<Creatorss>> Characters) throws Exception {

    }


}
