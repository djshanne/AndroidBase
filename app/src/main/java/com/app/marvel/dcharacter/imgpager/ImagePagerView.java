package com.app.marvel.dcharacter.imgpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.app.marvel.R;
import com.model.bean.characters.Thumbnail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by juan.delgado on 24/02/2017.
 */

public class ImagePagerView extends FrameLayout {

    @BindView(R.id.img_pager)
    ViewPager pager;
    private ImgAdapter imgAdapter;

    public ImagePagerView(Context context) {
        super(context);
        initView(context);
    }

    public ImagePagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ImagePagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.img_pager_layout, this);
        ButterKnife.bind(this);
        initializeViewPager();
    }

    private void initializeViewPager() {
        imgAdapter = new ImgAdapter();
        pager.setAdapter(imgAdapter);
        pager.animate().alpha(0).start();
    }

    public void setData(List<Thumbnail> attachments) {
        imgAdapter.setData(attachments);

        pager.animate().alpha(1).setDuration(4000).start();
    }


}
