package com.app.marvel.dcharacter;

import com.app.marvel.views.Controller;
import com.model.bean.characters.Result;

/**
 * Created by juan.delgado on 07/09/2016.
 */
public interface DetailFragmentController extends Controller {
    void renderView(Result register);
    Integer getSlug();
    void dismiss();
}
