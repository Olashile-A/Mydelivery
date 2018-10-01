package com.mydelivery.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;



public class CustomTextMedium extends TextView {
    public CustomTextMedium(Context context) {
        super(context);
    }

    public CustomTextMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Aller_Lt.ttf");
            setTypeface(tf);
        }
    }
}
