package com.mydelivery.yourDelivery;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.mydelivery.R;
import com.mydelivery.activity.PasswordResetActivitiy;
import com.mydelivery.activity.SignUpActivitiy;
import com.mydelivery.drawer.DrawerActivity;
import com.mydelivery.helper.CustomEditTextMedium;
import com.mydelivery.helper.CustomTextMedium;
import com.mydelivery.yourDelivery.fragment.AllFragment;
import com.mydelivery.yourDelivery.fragment.DeliveryFragment;
import com.mydelivery.yourDelivery.fragment.InProgressFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class YourDeliveryActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.toolbar_title)
    CustomTextMedium toolTitleTextView;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.tabs)
    TabLayout tabLayout;
    DeliveryPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_delivery);
        ButterKnife.inject(YourDeliveryActivity.this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_back);
        upArrow.setColorFilter(ContextCompat.getColor(YourDeliveryActivity.this, R.color.color_white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Click listner using here
        setListener();
        //End
        //Set the tab layout here
        setupViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);
        //End
        toolTitleTextView.setText("My Delivery Centre");

    }


    private void setListener() {

    }

    private void setupViewPager(ViewPager viewpager) {
        adapter = new DeliveryPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment(new BestOfferFragment(), "Best Offer");
        adapter.addFragment(new AllFragment(), "All");
        adapter.addFragment(new InProgressFragment(), "In Progress");
        adapter.addFragment(new DeliveryFragment(), "Delivered");
        viewpager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent yourDeliveryIntent = new Intent(YourDeliveryActivity.this, DrawerActivity.class);
        startActivity(yourDeliveryIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent yourDeliveryIntent = new Intent(YourDeliveryActivity.this, DrawerActivity.class);
                startActivity(yourDeliveryIntent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

}
