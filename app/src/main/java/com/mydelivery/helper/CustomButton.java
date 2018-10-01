package com.mydelivery.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


public class CustomButton extends Button {
    public CustomButton(Context context) {
        super(context);
        init(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Aller_Lt.ttf");
        setTypeface(font);
    }

}
