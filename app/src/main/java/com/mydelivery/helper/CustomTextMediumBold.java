package com.mydelivery.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class CustomTextMediumBold extends TextView {
    public CustomTextMediumBold(Context context) {
        super(context);
    }

    public CustomTextMediumBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextMediumBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/LATO-BOLD_2.TTF");
            setTypeface(tf);
        }
    }
}
