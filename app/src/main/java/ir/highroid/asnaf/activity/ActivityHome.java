package ir.highroid.asnaf.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.adapter.AdapterHomeViewPager;
import ir.highroid.asnaf.view.NonSwipeViewPager;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private NonSwipeViewPager viewPager;
    private AdapterHomeViewPager adapterHomeViewPager;
    ImageView btnHome, btnCall, btnInfo, btnPhotographer;

    public boolean isHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isHome = getIntent().getBooleanExtra("isHome", true);
        if (Build.VERSION.SDK_INT >= 21) {
            int colorId;
            if(!isHome){
                colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
            }
            else{
                colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark);
            }

            getWindow().setNavigationBarColor(colorId);
            getWindow().setStatusBarColor(colorId);
        }
        setContentView(R.layout.activity_home);
        initToolbar();
        initObjectAndView();
        initViewPager();
        initListenerAndEvent();
        if (!isHome) {
            btnHome.setImageResource(R.drawable.union_selected);
            int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
            btnHome.setColorFilter(colorId);
        } else {
            btnHome.setImageResource(R.drawable.home_selected);
        }
    }

    public void initObjectAndView() {
        viewPager = (NonSwipeViewPager) findViewById(R.id.viewpager);
        btnHome = (ImageView) findViewById(R.id.btnHome);
        btnPhotographer = (ImageView) findViewById(R.id.btnPhotographer);
        btnCall = (ImageView) findViewById(R.id.btnCall);
        btnInfo = (ImageView) findViewById(R.id.btnInfo);
    }

    private void initViewPager() {
        adapterHomeViewPager = new AdapterHomeViewPager(getSupportFragmentManager(), isHome);
        viewPager.setAdapter(adapterHomeViewPager);
    }

    private void initListenerAndEvent() {
        btnHome.setOnClickListener(this);
        btnPhotographer.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.btnHome:
                changeRibbonBar(viewId);
                viewPager.setCurrentItem(0, false);
                break;

            case R.id.btnPhotographer:
                changeRibbonBar(viewId);
                viewPager.setCurrentItem(1, false);
                break;

            case R.id.btnCall:
                changeRibbonBar(viewId);
                viewPager.setCurrentItem(2, false);
                break;

            case R.id.btnInfo:
                changeRibbonBar(viewId);
                viewPager.setCurrentItem(3, false);
                break;
        }
    }

    private void changeRibbonBar(int viewId) {
        switch (viewId) {
            case R.id.btnHome:
                if (!isHome) {
                    btnHome.setImageResource(R.drawable.union_selected);
                    int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
                    btnHome.setColorFilter(colorId);
                } else {
                    btnHome.setImageResource(R.drawable.home_selected);
                }
                btnPhotographer.setImageResource(R.drawable.asnaf);
                btnCall.setImageResource(R.drawable.call);
                btnInfo.setImageResource(R.drawable.info);
                btnPhotographer.clearColorFilter();
                btnCall.clearColorFilter();
                btnInfo.clearColorFilter();
                break;

            case R.id.btnPhotographer:
                if (!isHome) {
                    btnHome.setImageResource(R.drawable.union);
                    int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
                    btnPhotographer.setColorFilter(colorId);
                } else {
                    btnHome.setImageResource(R.drawable.home);
                }
                btnPhotographer.setImageResource(R.drawable.asnaf_selected);
                btnCall.setImageResource(R.drawable.call);
                btnInfo.setImageResource(R.drawable.info);
                btnHome.clearColorFilter();
                btnCall.clearColorFilter();
                btnInfo.clearColorFilter();
                break;

            case R.id.btnCall:
                if (!isHome) {
                    btnHome.setImageResource(R.drawable.union);
                    int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
                    btnCall.setColorFilter(colorId);
                } else {
                    btnHome.setImageResource(R.drawable.home);
                }                btnPhotographer.setImageResource(R.drawable.asnaf);
                btnCall.setImageResource(R.drawable.call_selected);
                btnInfo.setImageResource(R.drawable.info);
                btnHome.clearColorFilter();
                btnInfo.clearColorFilter();
                btnPhotographer.clearColorFilter();
                break;

            case R.id.btnInfo:
                if (!isHome) {
                    btnHome.setImageResource(R.drawable.union);
                    int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
                    btnInfo.setColorFilter(colorId);
                } else {
                    btnHome.setImageResource(R.drawable.home);
                }                btnPhotographer.setImageResource(R.drawable.asnaf);
                btnCall.setImageResource(R.drawable.call);
                btnInfo.setImageResource(R.drawable.info_selected);
                btnHome.clearColorFilter();
                btnPhotographer.clearColorFilter();
                btnCall.clearColorFilter();
                break;
        }
    }
}