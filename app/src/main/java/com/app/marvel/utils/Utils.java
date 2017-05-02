package com.app.marvel.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Juan Delgado on 3/29/2017.
 */

public class Utils {

    public static void setIconColor(Context c, int colorId, int drawableId, ImageView imageView) {
        if (c == null) return;
        Drawable d = c.getResources().getDrawable(drawableId);
        PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;
        d.setColorFilter(c.getResources().getColor(colorId), mMode);
        imageView.setImageDrawable(d);
    }

    public static Drawable setIconColor(Context c, int colorId, int drawableId) {
        if (c == null) return null;
        Drawable d = c.getResources().getDrawable(drawableId);
        PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;
        d.setColorFilter(c.getResources().getColor(colorId), mMode);
        return d;
    }


    public static SpannableStringBuilder getProcessedText(Context context, String string1, String string2, String string3, int color, String split) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        String chunk1 = string1.split(split)[0];
        String chunk2 = string1.split(split)[1];
        stringBuilder.append(chunk1);
        stringBuilder.append(string2);
        stringBuilder.append(chunk2);
        stringBuilder.append(string3);
        SpannableString span = new SpannableString(stringBuilder);
        span.setSpan(new ForegroundColorSpan(context.getResources().getColor(color)), stringBuilder.indexOf(string2), stringBuilder.indexOf(string2) + string2.length(), Spannable.SPAN_COMPOSING);
        span.setSpan(new ForegroundColorSpan(context.getResources().getColor(color)), stringBuilder.indexOf(string3), stringBuilder.indexOf(string3) + string3.length(), Spannable.SPAN_COMPOSING);
        builder.append(span);
        return builder;
    }

}
