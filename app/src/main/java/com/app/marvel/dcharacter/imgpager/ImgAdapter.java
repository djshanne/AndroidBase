package com.app.marvel.dcharacter.imgpager;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.marvel.R;
import com.model.bean.characters.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan.delgado on 24/02/2017.
 */

public class ImgAdapter extends PagerAdapter {

    List<Thumbnail> attachments = new ArrayList<>();

    @Override
    public int getCount() {
        return attachments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img = (ImageView) LayoutInflater.from(container.getContext()).inflate(R.layout.img_pager_img, null);
        Picasso.with(container.getContext()).load(attachments.get(position).getPath() + "." + attachments.get(position).getExtension()).into(img);
        container.addView(img);
        return img;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setData(List<Thumbnail> data) {
        this.attachments.clear();
        attachments.addAll(data);
        notifyDataSetChanged();
    }
}
