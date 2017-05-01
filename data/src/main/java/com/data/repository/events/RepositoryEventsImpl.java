package com.data.repository.events;

import com.data.repository.characters.Repository;
import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Eventss;

/**
 * Created by juan.delgado on 30/01/2017.
 */

public class RepositoryEventsImpl extends Repository implements RepositoryEvents {

    private final RepositoryServiceCloudEvents cloud;

    public RepositoryEventsImpl(TYPE type) {
        super(type);
        cloud = new RepositoryServiceCloudEvents();
    }

    @Override
    public BaseResponse<Data<Eventss>> getEvents(RequestList requestProfile) throws Exception {
        switch (type) {
            default:
                return cloud.getEvents(requestProfile);
        }
    }


    @Override
    public BaseResponse<Data<Eventss>> getEvent(RequestList RequestList) throws Exception {
        switch (type) {
            default:
                return cloud.getEvent(RequestList);
        }
    }

    @Override
    public void setEvents(BaseResponse<Data<Eventss>> profile) throws Exception {
    }
}
