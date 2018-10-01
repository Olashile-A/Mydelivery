package com.mydelivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import com.mydelivery.R;
import com.mydelivery.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AgreeActivitiy extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.layAccept)
    LinearLayout acceptLinearLayout;
    @InjectView(R.id.layDecline)
    LinearLayout declineLinearLayout;
    String mMobile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agree);
        ButterKnife.inject(AgreeActivitiy.this);

        //Set click listener here
        setListner();
        //End


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMobile = extras.getString("mobile_number");
            //End
        }
    }

    private void setListner() {
        acceptLinearLayout.setOnClickListener(this);
        declineLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layAccept:
                Intent enterMobileIntent = new Intent(AgreeActivitiy.this, LoginActivity.class);
                enterMobileIntent.putExtra("mobile_number", mMobile);
                startActivity(enterMobileIntent);
                break;
            case R.id.layDecline:
                Intent declineIntent = new Intent(AgreeActivitiy.this, SignUpActivitiy.class);
                declineIntent.putExtra("mobile_number", mMobile);
                startActivity(declineIntent);
                break;
        }
    }

}
