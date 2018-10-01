package com.mydelivery.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydelivery.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

;


public class HomeFragment extends Fragment implements View.OnClickListener {
    View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);

        //Using the click listener
        setListener(view);
        //End

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

