package com.app.marvel.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.app.marvel.R;
import com.app.marvel.base.MagicFont;


/**
 * Created by Juan Delgado on 6/24/2016.
 */
public class TextViewCustom extends AppCompatTextView {

    private String font;

    public TextViewCustom(Context context) {
        super(context);
        getFontFromAttrs(context, null);
        init();
    }

    public TextViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        getFontFromAttrs(context, attrs);
        init();
    }

    public TextViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getFontFromAttrs(context, attrs);
        init();
    }

    private void getFontFromAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.TextViewCustom);
            CharSequence font = arr.getString(R.styleable.TextViewCustom_font);
            if (font != null) {
                this.font = font.toString();
            }
            arr.recycle();
        }
    }

    private void init() {
        if (TextUtils.isEmpty(font)) {
            font = getContext().getResources().getString(MagicFont.DEFAULT_FONT);
        }
        Typeface face = Typeface.createFromAsset(getContext().getAssets(), font);
        setTypeface(face);

        /*int[][] states = new int[][]{
//                new int[]{android.R.attr.state_enabled}, // enabled
//                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{android.R.attr.state_selected}, // unchecked
                new int[]{-android.R.attr.state_selected}, // unchecked
//                new int[]{android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[]{
//                Color.BLACK,
                Color.RED,
                Color.GREEN,
//                Color.BLUE
        };

        ColorStateList myList = new ColorStateList(states, colors);
        setTextColor(myList);*/
    }

    public void setFont(int font) {
        Typeface face = Typeface.createFromAsset(getContext().getAssets(), getResources().getString(font));
        setTypeface(face);
    }
}
