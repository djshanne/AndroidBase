package com.data.repository.events;


import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Eventss;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public interface RepositoryEvents {
    public final String TAG = RepositoryEvents.class.getSimpleName();

    public BaseResponse<Data<Eventss>> getEvents(RequestList request) throws Exception;
    public BaseResponse<Data<Eventss>> getEvent(RequestList request) throws Exception;

    public void setEvents(BaseResponse<Data<Eventss>> events) throws Exception;


}
