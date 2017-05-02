package com.app.marvel;

import com.data.utils.Utils;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Result;
import com.model.bean.characters.Thumbnail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class UtilsUnitTest {

    @Test
    public void filtering_isCorrect() throws Exception {
        BaseResponse<Data<Result>> response = new BaseResponse<>();
        Data<Result> data = new Data<>();
        List<Result> results = new ArrayList<>();

        results.add(getResult("http://i.annihil.us/u/prod/marvel/i/mg/1/b0/5269678709fb7"));
        results.add(getResult("http://i.annihil.us/u/prod/marvel/i/mg/1/b0/5269678709fb7"));
        results.add(getResult("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"));
        data.setResults(results);
        response.setData(data);
        BaseResponse<Data<Result>> resultFiltered = new Utils<Result>().resultFiltered(response);
        assertEquals(2, resultFiltered.getData().getResults().size());
    }

    private Result getResult(String path) {
        Result result = new Result();
        result.setName("Name Test");
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setPath(path);
        result.setThumbnail(thumbnail);
        return result;
    }
}