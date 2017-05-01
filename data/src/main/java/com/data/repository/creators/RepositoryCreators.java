package com.data.repository.creators;


import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Creatorss;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public interface RepositoryCreators {
    public final String TAG = RepositoryCreators.class.getSimpleName();

    public BaseResponse<Data<Creatorss>> getCreators(RequestList request) throws Exception;
    public BaseResponse<Data<Creatorss>> getCreator(RequestList request) throws Exception;

    public void setCreators(BaseResponse<Data<Creatorss>> comics) throws Exception;


}
