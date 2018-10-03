package com.mydelivery.yourDelivery.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydelivery.R;
import com.mydelivery.yourDelivery.DeliveryPagerAdapter;
import com.mydelivery.yourDelivery.adapter.AllFragmentAdapter;
import com.mydelivery.yourDelivery.adapter.DeliveredAdapter;
import com.mydelivery.yourDelivery.adapter.ProgressAdapter;
import com.mydelivery.yourDelivery.customItem.DeliveryItem;
import com.mydelivery.yourDelivery.customItem.DummyData;
import com.mydelivery.yourDelivery.customItem.DummyDataDelivered;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DeliveryFragment extends Fragment {
    @InjectView(R.id.recycleView)
    RecyclerView deliveryRecyclerView;
    final ArrayList<DeliveryItem> deliveryItems = new ArrayList<>();
    DeliveredAdapter deliveredAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.delivery_fragment, container, false);
        ButterKnife.inject(DeliveryFragment.this, view);

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
        for (int i = 0; i < DummyDataDelivered.deliveryName.length; i++) {
            deliveryItems.add(new DeliveryItem(
                    DummyDataDelivered.deliveryImage[i],
                    DummyDataDelivered.deliveryName[i],
                    DummyDataDelivered.deliveryAddress[i]
            ));
        }

        deliveredAdapter = new DeliveredAdapter(getActivity(), deliveryItems);
        layoutManager = new LinearLayoutManager(getActivity());
        deliveryRecyclerView.setLayoutManager(layoutManager);
        deliveryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        deliveryRecyclerView.setAdapter(deliveredAdapter);
    }

    private void setListener() {

    }


}