package com.mydelivery.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.mydelivery.R;
import com.mydelivery.SplashActivity;
import com.mydelivery.activity.EnterOtpActivity;
import com.mydelivery.activity.ForgotActivitiy;
import com.mydelivery.activity.SignUpActivitiy;
import com.mydelivery.drawer.DrawerActivity;
import com.mydelivery.helper.CustomEditTextMedium;
import com.mydelivery.helper.CustomTextMedium;
import com.mydelivery.untils.Credentials;

import java.text.DateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener ,com.google.android.gms.location.LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
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
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    String mLastUpdateTime;

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();

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
        updateUI();
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
    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }
    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }

    private void updateUI() {
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());
            Log.i("lat Current:::", "lat Current:::" + lat);
            Log.i("lng Current:::", "lng Current:::" + lng);
            Credentials.saveLati(lat, LoginActivity.this);
            Credentials.saveLong(lng, LoginActivity.this);

        } else {

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();

        }
    }
}