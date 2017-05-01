package com.app.marvel.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Juan Delgado on 2/15/2017.
 */

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public LoadingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
