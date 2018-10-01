package com.mydelivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.mydelivery.R;
import com.mydelivery.helper.CustomEditTextMedium;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ForgotActivitiy extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.etPhone)
    CustomEditTextMedium phoneCustomEditTextMedium;
    @InjectView(R.id.layCancel)
    LinearLayout cancelLinearLayout;
    @InjectView(R.id.layRequest)
    LinearLayout requestLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.inject(ForgotActivitiy.this);
        //Set click listener here
        setListner();
        //End
    }

    private void setListner() {
        cancelLinearLayout.setOnClickListener(this);
        requestLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layRequest:
                if (phoneCustomEditTextMedium.length() == 0) {
                    Toast.makeText(this, "Please Enter your Phone number", Toast.LENGTH_SHORT).show();
                } else {
                    postData();
                }
                break;
            case R.id.layCancel:
                finish();
                break;
        }
    }

    private void postData() {
        Intent enterMobileIntent = new Intent(ForgotActivitiy.this, EnterOtpActivity.class);
        enterMobileIntent.putExtra("mobile_number", phoneCustomEditTextMedium.getText().toString());
        enterMobileIntent.putExtra("otp_value", "1");
        startActivity(enterMobileIntent);
    }
}
