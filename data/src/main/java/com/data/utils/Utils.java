package com.data.utils;

import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.comics.GenericThumb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Delgado on 6/15/2016.
 */
public class Utils<T> {

    public BaseResponse<Data<T>> resultFiltered(BaseResponse<Data<T>> resultBaseResponse) {
        List<T> results = resultBaseResponse.getData().getResults();
        List<T> resultsFiltered = new ArrayList<>();
        for (T result : results) {
            if (!((GenericThumb) result).getThumbnail().getPath().contains("image_not_available")) {
                resultsFiltered.add(result);
            }
        }
        resultBaseResponse.getData().setResults(resultsFiltered);
        return resultBaseResponse;
    }

}
