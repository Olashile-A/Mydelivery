package com.mydelivery.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mydelivery.R;
import com.mydelivery.helper.CustomEditTextMedium;
import com.mydelivery.helper.CustomTextMedium;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class EnterOtpActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.toolbar_title)
    CustomTextMedium toolTitleTextView;
    @InjectView(R.id.txtEnterMobile)
    CustomTextMedium enterMobileCustomTextMedium;
    @InjectView(R.id.laySubmit)
    LinearLayout submitLinearLayout;
    @InjectView(R.id.otp_1)
    CustomEditTextMedium oneEditText;
    @InjectView(R.id.otp_2)
    CustomEditTextMedium twoEditText;
    @InjectView(R.id.otp_3)
    CustomEditTextMedium threeEditText;
    @InjectView(R.id.otp_4)
    CustomEditTextMedium fourEditText;
    String mMobile, text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterotp);
        ButterKnife.inject(EnterOtpActivity.this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_back);
        upArrow.setColorFilter(ContextCompat.getColor(EnterOtpActivity.this, R.color.color_black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Click listner using here
        setListener();
        //End

        toolTitleTextView.setText("");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMobile = extras.getString("mobile_number");
            //End
            enterMobileCustomTextMedium.setText("Enter the 4 digit code sent to you at " + mMobile + getResources().getString(R.string.correct_number));
        }
    }


    private void setListener() {
        submitLinearLayout.setOnClickListener(this);

        oneEditText.addTextChangedListener(new GenericTextWatcher(oneEditText));
        twoEditText.addTextChangedListener(new GenericTextWatcher(twoEditText));
        threeEditText.addTextChangedListener(new GenericTextWatcher(threeEditText));
        fourEditText.addTextChangedListener(new GenericTextWatcher(fourEditText));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.laySubmit:
                Intent enterMobileIntent = new Intent(EnterOtpActivity.this, SignUpActivitiy.class);
                enterMobileIntent.putExtra("mobile_number", mMobile);
                startActivity(enterMobileIntent);
                break;
        }
    }

    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            text = editable.toString();
            switch (view.getId()) {
                case R.id.otp_1:
                    if (text.length() == 1)
                        twoEditText.requestFocus();
                    oneEditText.setEnabled(false);
                    break;
                case R.id.otp_2:
                    if (text.length() == 1)
                        threeEditText.requestFocus();
                    twoEditText.setEnabled(false);

                    if (text.length() == 0)
                        oneEditText.requestFocus();

                    break;
                case R.id.otp_3:
                    if (text.length() == 1)
                        fourEditText.requestFocus();
                    threeEditText.setEnabled(false);

                    if (text.length() == 0)
                        twoEditText.requestFocus();


                    break;
                case R.id.otp_4:

                    if (text.length() == 0)
                        threeEditText.requestFocus();
                    String mOtp = oneEditText.getText().toString() + "" + twoEditText.getText().toString() + "" + threeEditText.getText().toString() + "" + fourEditText.getText().toString();

                    break;

            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }
}
