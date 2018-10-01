package com.mydelivery.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.mydelivery.R;

import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(LoginActivity.this);

        //Set listener
        setListener();
        //End
    }

    private void setListener() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
