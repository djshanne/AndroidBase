package com.data.repository.events;

import com.data.repository.RepositoryServiceCloudBase;
import com.data.service.Cloud;
import com.data.service.request.RequestList;
import com.data.utils.Utils;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Eventss;
import com.model.bean.characters.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public class RepositoryServiceCloudEvents extends RepositoryServiceCloudBase implements RepositoryEvents {

    public RepositoryServiceCloudEvents() {
    }

    @Override
    public BaseResponse<Data<Eventss>> getEvents(RequestList requestCharacters) throws Exception {
        Response<BaseResponse<Data<Eventss>>> r = Cloud.getApiApp().getEvents(getApiKey(), getHash(), getTs(), requestCharacters.getPage()).execute();
        switch (r.code()) {
            default:
                return new Utils<Eventss>().resultFiltered((BaseResponse<Data<Eventss>>) handleResponse(r));
        }
    }


    @Override
    public BaseResponse<Data<Eventss>> getEvent(RequestList RequestList) throws Exception {
        Response<BaseResponse<Data<Result>>> r;
        r = Cloud.getApiApp().getEvent(RequestList.getId(), getApiKey(), getHash(), getTs(), RequestList.getPage()).execute();
        switch (r.code()) {
            default:
                return (BaseResponse<Data<Eventss>>) handleResponse(r);
        }
    }



    @Override
    public void setEvents(BaseResponse<Data<Eventss>> Characters) throws Exception {

    }


}
