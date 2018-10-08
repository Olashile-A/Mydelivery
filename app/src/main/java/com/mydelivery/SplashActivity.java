package com.mydelivery;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.mukesh.permissions.AppPermissions;
import com.mydelivery.activity.WelcomeActivitiy;
import com.mydelivery.drawer.DrawerActivity;
import com.mydelivery.untils.Credentials;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SplashActivity extends AppCompatActivity implements com.google.android.gms.location.LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    private Intent intent;
    private Handler handler;
    private Runnable runnable;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    private static final int ALL_REQUEST_CODE = 0;
    AppPermissions mRuntimePermission;
    String mLastUpdateTime;

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
    }

    private static final String[] ALL_PERMISSIONS = {
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();

        setContentView(R.layout.activity_splash);
        ButterKnife.inject(SplashActivity.this);


        mRuntimePermission = new AppPermissions(SplashActivity.this);
        if (Build.VERSION.SDK_INT >= 23) {
            if (mRuntimePermission.hasPermission(ALL_PERMISSIONS)) {
                updateUI();
                exitSplash();
            } else {
                mRuntimePermission.requestPermission(SplashActivity.this, ALL_PERMISSIONS, ALL_REQUEST_CODE);
            }
        } else {
            updateUI();
            exitSplash();
        }



    }
    public void exitSplash() {
        final int SPLASH_TIME_OUT = 2200;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("run", "run: ");
                intent = new Intent(SplashActivity.this, WelcomeActivitiy.class);
                startActivity(intent);
                finish();

            }
        };
        handler.postDelayed(runnable, SPLASH_TIME_OUT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ALL_REQUEST_CODE:
                List<Integer> permissionResults = new ArrayList<>();
                for (int grantResult : grantResults) {
                    permissionResults.add(grantResult);
                }
                if (permissionResults.contains(PackageManager.PERMISSION_DENIED)) {
                    mRuntimePermission.requestPermission(SplashActivity.this, ALL_PERMISSIONS, ALL_REQUEST_CODE);
                    Toast.makeText(SplashActivity.this, "All permissions are necessary, please select allow saveButton from permission dialog.", Toast.LENGTH_SHORT).show();
                } else {
                    updateUI();
                    exitSplash();
                }
                break;
        }
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
            Credentials.saveLati(lat, SplashActivity.this);
            Credentials.saveLong(lng, SplashActivity.this);

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
