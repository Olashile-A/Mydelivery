package com.mydelivery.login;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mydelivery.R;
import com.mydelivery.activity.EnterOtpActivity;
import com.mydelivery.activity.ForgotActivitiy;
import com.mydelivery.activity.SignUpActivitiy;
import com.mydelivery.drawer.DrawerActivity;
import com.mydelivery.helper.CustomEditTextMedium;
import com.mydelivery.helper.CustomTextMedium;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.toolbar_title)
    CustomTextMedium toolTitleTextView;
    @InjectView(R.id.etPhone)
    CustomEditTextMedium mobileCustomTextMedium;
    @InjectView(R.id.etPassword)
    CustomEditTextMedium passwordCustomEditTextMedium;
    @InjectView(R.id.layLogin)
    LinearLayout loginLinearLayout;
    @InjectView(R.id.txtForgot)
    CustomTextMedium forgotCustomTextMedium;
    String mMobile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(LoginActivity.this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_back);
        upArrow.setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.color_black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Click listner using here
        setListener();
        //End

        toolTitleTextView.setText("");

    }


    private void setListener() {
        loginLinearLayout.setOnClickListener(this);
        forgotCustomTextMedium.setOnClickListener(this);
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
            case R.id.layLogin:
                if (mobileCustomTextMedium.length() == 0) {
                    Toast.makeText(this, "Please Enter your Phone number", Toast.LENGTH_SHORT).show();
                } else if (passwordCustomEditTextMedium.length() == 0) {
                    Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
                } else {
                    postlogin();
                }
                break;
            case R.id.txtForgot:
                Intent enterMobileIntent = new Intent(LoginActivity.this, ForgotActivitiy.class);
                startActivity(enterMobileIntent);
                break;
        }
    }

    private void postlogin() {
        Intent enterMobileIntent = new Intent(LoginActivity.this, DrawerActivity.class);
        startActivity(enterMobileIntent);
    }

}