
package com.model.bean.characters.comics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.model.bean.characters.Characters;
import com.model.bean.characters.Creators;
import com.model.bean.characters.Date;
import com.model.bean.characters.Events;
import com.model.bean.characters.Image;
import com.model.bean.characters.Price;
import com.model.bean.characters.Serie;
import com.model.bean.characters.Stories;
import com.model.bean.characters.Thumbnail;
import com.model.bean.characters.Url;

import java.util.List;

public class GenericThumb{

    @SerializedName("thumbnail")
    @Expose
    protected Thumbnail thumbnail;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
