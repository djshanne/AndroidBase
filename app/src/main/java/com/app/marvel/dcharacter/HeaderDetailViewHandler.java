package com.app.marvel.dcharacter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.app.marvel.R;
import com.app.marvel.base.BaseVariantsViewHandler;
import com.app.marvel.info.InfoActivity;
import com.app.marvel.views.ButtonCustom;
import com.app.marvel.views.TextViewCustom;
import com.model.bean.characters.Item;
import com.model.bean.characters.Result;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Juan Delgado on 3/29/2017.
 */

public class HeaderDetailViewHandler extends BaseVariantsViewHandler {
    private final DetailFragmentController controller;
    @BindView(R.id.detail_main_header_screen_title)
    TextViewCustom title;
    @BindView(R.id.stories_title)
    TextViewCustom storiesTitle;
    @BindView(R.id.detail_main_header_screen_location)
    TextViewCustom desc;
    @BindView(R.id.detail_main_header_screen_options)
    ButtonCustom optionsButton;
    @BindView(R.id.detail_main_stories)
    LinearLayout stories;


    public HeaderDetailViewHandler(DetailFragmentController controller) {
        this.controller = controller;
    }

    public void setView(View view) {
        super.setView(view);
        storiesTitle.setFont(R.string.Marvel);
        title.setFont(R.string.Marvel);
        optionsButton.setFont(R.string.Marvel);
    }

    public void renderView(Result data) {
        title.setText(data.getName());
        desc.setText(data.getDescription());
        stories.removeAllViews();
        for (Item i : data.getStories().getItems()) {
            View v = LayoutInflater.from(getView().getContext()).inflate(R.layout.item_story_wide, null);
            ((TextViewCustom) v.findViewById(R.id.name_story)).setText(i.getName());
            stories.addView(v);
        }

    }


    @OnClick(R.id.detail_main_header_screen_options)
    public void onOptionsClicked() {
        InfoActivity.startWithId(controller.getCtx(), controller.getSlug(), title.getText().toString());
    }

}
