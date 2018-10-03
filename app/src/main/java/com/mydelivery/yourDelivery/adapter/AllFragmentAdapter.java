package com.mydelivery.yourDelivery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mydelivery.R;
import com.mydelivery.helper.CustomTextMedium;
import com.mydelivery.helper.CustomTextMediumBold;
import com.mydelivery.yourDelivery.customItem.DeliveryItem;

import java.util.ArrayList;

public class AllFragmentAdapter extends RecyclerView.Adapter<AllFragmentAdapter.MyViewHolder> {
    private ArrayList<DeliveryItem> dataSet;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView deliveryCircleImageView;
        CustomTextMediumBold deliveryName;
        CustomTextMedium deliveryAddress;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.deliveryCircleImageView = itemView.findViewById(R.id.imgDelivery);
            this.deliveryName = itemView.findViewById(R.id.txtDeliveryName);
            this.deliveryAddress = itemView.findViewById(R.id.txtDeliveryAddress);

        }
    }

    public AllFragmentAdapter(Context context, ArrayList<DeliveryItem> data) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public AllFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_row_item, parent, false);
        AllFragmentAdapter.MyViewHolder myViewHolder = new AllFragmentAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final AllFragmentAdapter.MyViewHolder holder, final int listPosition) {
        holder.deliveryName.setText(dataSet.get(listPosition).getNameDelivery());
        holder.deliveryAddress.setText(dataSet.get(listPosition).getAddressDelivery());
        holder.deliveryCircleImageView.setImageResource(dataSet.get(listPosition).getImageDelivery());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();

    }

}
