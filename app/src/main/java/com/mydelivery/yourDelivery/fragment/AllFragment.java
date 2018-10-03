package com.mydelivery.yourDelivery.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mydelivery.R;
import com.mydelivery.yourDelivery.adapter.AllFragmentAdapter;
import com.mydelivery.yourDelivery.customItem.DeliveryItem;
import com.mydelivery.yourDelivery.customItem.DummyData;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AllFragment extends Fragment {
    @InjectView(R.id.recycleView)
    RecyclerView deliveryRecyclerView;
    final ArrayList<DeliveryItem> deliveryItems = new ArrayList<>();
    AllFragmentAdapter allFragmentAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.delivery_fragment, container, false);
        ButterKnife.inject(AllFragment.this, view);

        //Set listener
        setListener();
        //End

        //Getting the operator data
        getAllData();
        //End

        return view;
    }

    private void getAllData() {
        deliveryItems.clear();
        for (int i = 0; i < DummyData.deliveryName.length; i++) {
            deliveryItems.add(new DeliveryItem(
                    DummyData.deliveryImage[i],
                    DummyData.deliveryName[i],
                    DummyData.deliveryAddress[i]
            ));
        }

        allFragmentAdapter = new AllFragmentAdapter(getActivity(), deliveryItems);
        layoutManager = new LinearLayoutManager(getActivity());
        deliveryRecyclerView.setLayoutManager(layoutManager);
        deliveryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        deliveryRecyclerView.setAdapter(allFragmentAdapter);
    }

    private void setListener() {

    }


}