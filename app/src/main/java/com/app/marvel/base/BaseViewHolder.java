package com.app.marvel.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by juan.delgado on 17/02/2017.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    protected View mainView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mainView = itemView;
        ButterKnife.bind(this, itemView);


        mainView.setOnClickListener(view -> {
            onItemCLicked();
            onItemSelected();
        });
    }

    public View getMainView() {
        return mainView;
    }

    protected abstract void onItemCLicked();

    protected abstract void onItemSelected();

    protected Context getContext() {
        return mainView.getContext();
    }

}
