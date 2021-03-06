package com.mydelivery.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by rajat singh on 1/6/17.
 */

public class CustomTextItalic extends TextView {
    public CustomTextItalic(Context context) {
        super(context);
    }

    public CustomTextItalic(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/LATO-ITALIC_2.TTF");
            setTypeface(tf);
        }
    }
}
