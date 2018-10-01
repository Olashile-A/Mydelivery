package com.mydelivery.drawer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mydelivery.R;
import com.mydelivery.fragment.HomeFragment;
import com.mydelivery.helper.CustomTextMedium;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DrawerActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.toolbar_title)
    CustomTextMedium toolTitle;
    @InjectView(R.id.layHome)
    LinearLayout homeLinearLayout;
    @InjectView(R.id.user_text)
    CustomTextMedium usernameTextView;
    @InjectView(R.id.layRate)
    LinearLayout rateLinearLayout;
    @InjectView(R.id.layDraweHeader)
    LinearLayout layDraweHeader;
    @InjectView(R.id.layShare)
    LinearLayout shareLinearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.inject(DrawerActivity.this);


        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar,
                R.string.app_name, R.string.app_name) {
        };
        mDrawerToggle.syncState();

        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.syncState();
        mDrawerLayout.closeDrawer(Gravity.START);


        fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = new HomeFragment();
        inflateFragment(homeFragment);

        /***Toolbar setup*/
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        setupDrawerToggle();
        setListeners();

    }



    @Override
    public void onBackPressed() {
        //showDialog();
        finish();
    }

    /**
     * Listeners
     */
    public void setListeners() {
        layDraweHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(Gravity.START);
            }
        });
        homeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                inflateFragment(homeFragment);
            }
        });


        rateLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.roadtraffic.signsindia"));
                startActivity(i);
            }
        });


        shareLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBodyText = "Take a look at this amazing app: https://play.google.com/store/apps/details?id=com.roadtraffic.signsindia";
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject/Title");
                intent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(intent, "Share with"));
            }
        });
    }


    /**
     * ActionBarToggle Button Setup
     */
    void setupDrawerToggle() {
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar,
                R.string.app_name, R.string.app_name) {
        };
        mDrawerToggle.syncState();

        mDrawerToggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.mipmap.icon_menu, getApplicationContext().getTheme());
        mDrawerToggle.setHomeAsUpIndicator(drawable);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        mDrawerToggle.syncState();
        mDrawerLayout.closeDrawer(Gravity.START);
    }


    /**
     * Set Toolbar
     */
    public void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolBarTextView = toolbar.findViewById(R.id.toolbar_title);
        /// toolBarTextView.setText("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * to handle drawer Post Click
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /**
     * to handle idle click of drawer
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                mDrawerLayout.closeDrawer(Gravity.START);
            } else {
                mDrawerLayout.openDrawer(Gravity.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item) | mDrawerToggle.onOptionsItemSelected(item);
    }


    public void inflateFragment(Fragment frg) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, frg);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        mDrawerLayout.closeDrawer(Gravity.START);
    }


    /*public void showDialog() {
        final Dialog dialog = new Dialog(DrawerActivity.this, R.style.customDialog);
        dialog.setContentView(R.layout.exist_dialog);
        Button no = dialog.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button yes = dialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.show();
    }
*/


}
