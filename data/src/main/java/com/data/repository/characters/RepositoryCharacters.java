package com.data.repository.characters;


import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public interface RepositoryCharacters {
    public final String TAG = RepositoryCharacters.class.getSimpleName();

    public BaseResponse<Data<Result>> getCharacters(RequestList requestCharacters) throws Exception;
    public BaseResponse<Data<Result>> getCharacter(RequestList requestCharacters) throws Exception;

    public void setCharacters(BaseResponse<Data<Result>> profile) throws Exception;


}
