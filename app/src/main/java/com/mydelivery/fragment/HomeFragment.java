package com.mydelivery.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mydelivery.R;
import com.mydelivery.untils.Credentials;

import butterknife.ButterKnife;
import butterknife.InjectView;

;


public class HomeFragment extends Fragment implements View.OnClickListener {
    View view = null;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);

        //Using the click listener
        setListener(view);
        //End

        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    double lati = 0,longi=0;
                    if(Credentials.getLati(getActivity()).equals("")){

                    }
                    else {
                       lati = Double.parseDouble(Credentials.getLati(getActivity()));
                       longi = Double.parseDouble(Credentials.getLong(getActivity()));
                    }
                    LatLng latLng = new LatLng(lati, longi);
                    googleMap.addMarker(new MarkerOptions().position(latLng));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                }
            });
        }

        // R.id.map is a FrameLayout, not a Fragment
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();

        return view;

    }

    private void setListener(View view) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

}

