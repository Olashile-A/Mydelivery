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
import com.mydelivery.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivitiy extends AppCompatActivity implements View.OnClickListener {
    CountryCodePicker ccp;
    private String countrySelectedCode;
    @InjectView(R.id.mobile_no)
    CustomEditTextMedium mobileCustomEditTextMedium;
    @InjectView(R.id.layEnter)
    LinearLayout enterLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(WelcomeActivitiy.this);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        //Set click listener here
        setListner();
        //End
    }

    private void setListner() {
        countrySelectedCode = ccp.getSelectedCountryCode();

        enterLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layEnter:
                if (mobileCustomEditTextMedium.length() == 0) {
                    Toast.makeText(this, "Please Enter your Phone number", Toast.LENGTH_SHORT).show();
                } else {
                    postData();
                }
                break;
        }
    }

    private void postData() {
        countrySelectedCode = ccp.getSelectedCountryCode();
        Intent enterMobileIntent= new Intent(WelcomeActivitiy.this,EnterOtpActivity.class);
        enterMobileIntent.putExtra("mobile_number",mobileCustomEditTextMedium.getText().toString());
        startActivity(enterMobileIntent);
    }
}
