package com.app.marvel.info;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.app.marvel.R;
import com.app.marvel.base.BaseViewHolder;
import com.app.marvel.base.ItemListener;
import com.app.marvel.views.TextViewCustom;
import com.model.bean.characters.Result;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by Juan Delgado on 2/15/2017.
 */

public class InfoViewHolder extends BaseViewHolder {


    private final Picasso picasso;


    @BindView(R.id.name)
    TextViewCustom name;

    @BindView(R.id.img)
    ImageView img;

    private ItemListener listener;
    private Result item;

    public InfoViewHolder(View itemView) {
        super(itemView);
        picasso = Picasso.with(itemView.getContext());
    }

    public void bind(Result item, ItemListener listener) {
        this.item = item;
        this.listener = listener;
//        this.name.setText(item.getUuid() + item.getTitle());
        this.name.setText(item.getName());
        if (!TextUtils.isEmpty(item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension()))
            picasso.load(item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension()).into(img);
    }

    @Override
    protected void onItemCLicked() {
        if (listener != null)
            listener.onItemClicked(item);
    }

    @Override
    protected void onItemSelected() {

    }
}
