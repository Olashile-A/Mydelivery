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
import com.mydelivery.helper.CustomTextMedium;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignUpActivitiy extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.layEnter)
    LinearLayout enterLinearLayout;
    @InjectView(R.id.etFirstName)
    CustomEditTextMedium firstName;
    @InjectView(R.id.etLastName)
    CustomEditTextMedium lastName;
    @InjectView(R.id.etMobileNumer)
    CustomTextMedium mobileNumber;
    @InjectView(R.id.etEmail)
    CustomEditTextMedium email;
    @InjectView(R.id.etPassword)
    CustomEditTextMedium password;
    String mMobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(SignUpActivitiy.this);
        //Set click listener here
        setListner();
        //End


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMobile = extras.getString("mobile_number");
            //End
            mobileNumber.setText(mMobile);
           }
    }

    private void setListner() {
        enterLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layEnter:
                if (firstName.length() == 0) {
                    Toast.makeText(this, "Please Enter your First name", Toast.LENGTH_SHORT).show();
                }
                else if (lastName.length() == 0) {
                    Toast.makeText(this, "Please Enter your last name", Toast.LENGTH_SHORT).show();
                }

                else if (email.length() == 0) {
                    Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() == 0) {
                    Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    postData();
                }
                break;
        }
    }

    private void postData() {
        Toast.makeText(this, "Successfully Submit.", Toast.LENGTH_SHORT).show();
    }
}
