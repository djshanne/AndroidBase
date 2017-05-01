package com.data.repository.series;


import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Seriess;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public interface RepositorySeries {
    public final String TAG = RepositorySeries.class.getSimpleName();

    public BaseResponse<Data<Seriess>> getCharacters(RequestList requestCharacters) throws Exception;
    public BaseResponse<Data<Seriess>> getCharacter(RequestList requestCharacters) throws Exception;

    public void setCharacters(BaseResponse<Data<Seriess>> profile) throws Exception;


}
