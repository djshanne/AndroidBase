package com.app.marvel.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by juan.delgado on 09/01/2017.
 */

public class VerticalViewPager extends ViewPager {
    //http://stackoverflow.com/questions/13914609/viewpager-with-previous-and-next-page-boundaries
    private static final String TAG = VerticalViewPager.class.getSimpleName();
    private float mDownX;
    ViewConfiguration vc ;
    private int mSlop;

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer(0.08F, 0.0F, 0.0F, 0F));
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);

        vc = ViewConfiguration.get(getContext());
        mSlop = vc.getScaledTouchSlop();
    }


    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();
        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;
        ev.setLocation(newX, newY);
        return ev;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(swapXY(ev));
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mDownX = ev.getRawY();
            }
            case MotionEvent.ACTION_MOVE: {
                float deltaX = ev.getRawY() - mDownX;
                if (Math.abs(deltaX) > mSlop) {
                    Log.d("SCROLL","SWIPE");
                    result = true;
                }else
                    result = false;
            }
        }
        swapXY(ev);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapXY(ev));
    }


    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }

    public class CoverTransformer implements PageTransformer {
        public static final String TAG = "CoverTransformer";
        public static final float SCALE_MIN = 0.3F;
        public static final float SCALE_MAX = 1.0F;
        public static final float MARGIN_MIN = 0.0F;
        public static final float MARGIN_MAX = 50.0F;
        public float scale = 0.0F;
        private float pagerMargin = 0.0F;
        private float spaceValue = 0.0F;
        private float rotationX = 0.0F;
        private float rotationY = 0.0F;

        public CoverTransformer(float scale, float pagerMargin, float spaceValue, float rotationY) {
            this.scale = scale;
            this.pagerMargin = pagerMargin;
            this.spaceValue = spaceValue;
            this.rotationY = rotationY;

        }

        public void transformPage(View page, float position) {
//            Log.d("CoverTransformer", "position:" + position);
            float realPagerMargin;
            if (this.rotationY != 0.0F) {
                realPagerMargin = Math.min(this.rotationY, Math.abs(position * this.rotationY));
                page.setRotationY(position < 0.0F ? realPagerMargin : -realPagerMargin);
            }

            if (this.scale != 0.0F) {
                realPagerMargin = getFloat(1.0F - Math.abs(position * this.scale), 0.3F, 1.0F);
                page.setScaleX(realPagerMargin);
                page.setScaleY(realPagerMargin);
            }

            if (this.pagerMargin != 0.0F) {
                realPagerMargin = position * this.pagerMargin;
                if (this.spaceValue != 0.0F) {
                    float realSpaceValue = getFloat(Math.abs(position * this.spaceValue), 0.0F, 50.0F);
                    realPagerMargin += position > 0.0F ? realSpaceValue : -realSpaceValue;
                }

                page.setTranslationX(realPagerMargin);
            }

        }

    }

    private float getFloat(float value, float minValue, float maxValue) {
        return Math.min(maxValue, Math.max(minValue, value));
    }

    private class VerticalPageTransformer extends CoverTransformer {

        public VerticalPageTransformer(float scale, float pagerMargin, float spaceValue, float rotationY) {
            super(scale, pagerMargin, spaceValue, rotationY);
        }

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);

                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
            super.transformPage(view, position);
        }
    }

}
