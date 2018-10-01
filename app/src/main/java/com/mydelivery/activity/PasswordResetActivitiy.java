package com.mydelivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mydelivery.R;
import com.mydelivery.helper.CustomEditTextMedium;
import com.mydelivery.helper.CustomTextMedium;
import com.mydelivery.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PasswordResetActivitiy extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.laySubmit)
    LinearLayout submitLinearLayout;
    @InjectView(R.id.etTemporyPassword)
    CustomEditTextMedium temporyPassword;
    @InjectView(R.id.etNewPassword)
    CustomEditTextMedium newpassword;
    @InjectView(R.id.etConfirmPassword)
    CustomEditTextMedium confirmPassword;
    @InjectView(R.id.txtResetNumber)
    CustomTextMedium resetPassword;
    String mMobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        ButterKnife.inject(PasswordResetActivitiy.this);

        //Set click listener here
        setListner();
        //End


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMobile = extras.getString("mobile_number");
            //End
            resetPassword.setText("Reset your password using the tempory password set tomMobile " + mMobile);
        }
    }

    private void setListner() {
        submitLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.laySubmit:
                if (temporyPassword.length() == 0) {
                    Toast.makeText(this, "Please Enter your Tempory Password", Toast.LENGTH_SHORT).show();
                } else if (newpassword.length() == 0) {
                    Toast.makeText(this, "Please Enter your New Password", Toast.LENGTH_SHORT).show();
                } else if (confirmPassword.length() == 0) {
                    Toast.makeText(this, "Please Enter your Confirm Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    postData();
                }
                break;
        }
    }

    private void postData() {
        Intent enterMobileIntent = new Intent(PasswordResetActivitiy.this, LoginActivity.class);
        startActivity(enterMobileIntent);
    }
}
