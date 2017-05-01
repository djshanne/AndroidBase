package com.app.marvel.base;

import android.view.View;
import android.widget.ImageView;


import com.app.marvel.R;
import com.app.marvel.utils.Utils;

import butterknife.BindView;

/**
 * Created by Juan Delgado on 2/15/2017.
 */

public class RetryViewHolder extends BaseViewHolder {

    @BindView(R.id.listItemRetry)
    ImageView retry;

    private RetryListener listener;

    public RetryViewHolder(View itemView) {
        super(itemView);
        Utils.setIconColor(getContext(),android.R.color.white,R.drawable.ic_replay_black_24dp,retry);
    }

    @Override
    protected void onItemCLicked() {
        if (listener != null)
            listener.onRetryClicked();
    }

    @Override
    protected void onItemSelected() {

    }

    public void bind(RetryListener listener) {
        this.listener = listener;
    }

}
