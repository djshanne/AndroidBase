package com.app.marvel.comics;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.app.marvel.R;
import com.app.marvel.base.BaseViewHolder;
import com.app.marvel.base.ItemListener;
import com.app.marvel.views.TextViewCustom;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by Juan Delgado on 2/15/2017.
 */

public class ComicsViewHolder extends BaseViewHolder {


    private final Picasso picasso;


    @BindView(R.id.name)
    TextViewCustom name;

    @BindView(R.id.price)
    TextViewCustom price;

    @BindView(R.id.img)
    ImageView img;

    private ItemListener listener;
    private com.model.bean.characters.comics.Comics item;

    public ComicsViewHolder(View itemView) {
        super(itemView);
        picasso = Picasso.with(itemView.getContext());
    }

    public void bind(com.model.bean.characters.comics.Comics item, ItemListener listener) {
        this.item = item;
        this.listener = listener;
        this.name.setText(item.getTitle());
        if(!item.getPrices().isEmpty()){
            price.setVisibility(View.VISIBLE);
            this.price.setText(formatDecimal(item.getPrices().get(0).getPrice()));
            this.price.setFont(R.string.Marvel);
        }


        if (!TextUtils.isEmpty(item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension()))
            picasso.load(item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension()).into(img);

    }

    @Override
    protected void onItemCLicked() {
        if (listener != null)
            listener.onItemClicked(item);
    }

    public String formatDecimal(float number) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setCurrency(Currency.getInstance(Locale.FRANCE));
        return formatter.format(number);
    }

    @Override
    protected void onItemSelected() {

    }
}
