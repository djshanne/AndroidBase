package com.data.repository.comics;


import com.data.service.request.RequestList;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.Comics;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public interface RepositoryComics {
    public final String TAG = RepositoryComics.class.getSimpleName();

    public BaseResponse<Data<Comics>> getComics(RequestList request) throws Exception;
    public BaseResponse<Data<Comics>> getComic(RequestList request) throws Exception;

    public void setComics(BaseResponse<Data<Comics>> comics) throws Exception;


}
