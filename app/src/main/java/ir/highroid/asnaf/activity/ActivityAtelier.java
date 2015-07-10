package ir.highroid.asnaf.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.adapter.AdapterPhotographerGalleryTabRecycler;
import ir.highroid.asnaf.adapter.AdapterPhotographerImageGalleryRecycler;
import ir.highroid.asnaf.bundle.BundlePhotographerImageGallery;
import ir.highroid.asnaf.bundle.BundlePhotographerTab;

public class ActivityAtelier extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView photographerTabRecycler, photographerImageGalleryRecycler;
    private LinearLayoutManager linearLayoutManager, linearLayoutManager1;
    TextView txtManager, txtBusinessLicense, txtPhone, txtAtelierAbout, txtAtelierAboutTitle, txtToolbar;
    AppCompatButton btnMapAddress;
    Typeface typeface;
    String[] imageTabTitle = {"مد", "پرتره", "عروسی", "دیجیتال", "کودک"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
            getWindow().setNavigationBarColor(colorId);

            getWindow().setStatusBarColor(colorId);
        }

        setContentView(R.layout.activity_atelier);
        initToolbar();
        initObjectAndViews();
        initTabRecycler();
        initImageGalleryRecycler();
    }

    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        typeface = Typeface.createFromAsset(getAssets(), "IRAN Sans.ttf");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        txtToolbar = (TextView) findViewById(R.id.txtToolbar);
        txtToolbar.setTypeface(typeface);
    }

    public void initTabRecycler(){
        List<BundlePhotographerTab> photographerTabList = new ArrayList<>();

        for (int i = 0; i < imageTabTitle.length; i++) {
            BundlePhotographerTab bundlePhotographerTab = new BundlePhotographerTab();
            bundlePhotographerTab.title = "گالری " + imageTabTitle[i];
            photographerTabList.add(bundlePhotographerTab);
        }

        AdapterPhotographerGalleryTabRecycler adapterGalleryRecycler = new AdapterPhotographerGalleryTabRecycler(photographerTabList, getApplicationContext());
        photographerTabRecycler.setLayoutManager(linearLayoutManager);
        photographerTabRecycler.setAdapter(adapterGalleryRecycler);

    }


    public void initImageGalleryRecycler(){
        List<BundlePhotographerImageGallery> photographerImageGalleryList = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            BundlePhotographerImageGallery bundlePhotographerImageGallery = new BundlePhotographerImageGallery();
            bundlePhotographerImageGallery.image = "c" + i;
            photographerImageGalleryList.add(bundlePhotographerImageGallery);
        }

        AdapterPhotographerImageGalleryRecycler adapterPhotographerImageGalleryRecycler = new AdapterPhotographerImageGalleryRecycler(photographerImageGalleryList, getApplicationContext());
        photographerImageGalleryRecycler.setLayoutManager(linearLayoutManager1);
        photographerImageGalleryRecycler.setAdapter(adapterPhotographerImageGalleryRecycler);

    }

    private void initObjectAndViews(){
        photographerTabRecycler = (RecyclerView) findViewById(R.id.photographerGalleryTabRecycler);
        photographerImageGalleryRecycler = (RecyclerView) findViewById(R.id.photographerImageGalleryRecycler);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        txtBusinessLicense = (TextView) findViewById(R.id.txtBusinessLicense);
        txtManager = (TextView) findViewById(R.id.txtManager);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtAtelierAbout = (TextView) findViewById(R.id.txtAtelierAbout);
        txtAtelierAboutTitle = (TextView) findViewById(R.id.txtAtelierAboutTitle);
        btnMapAddress = (AppCompatButton) findViewById(R.id.btnMapAddress);


        txtBusinessLicense.setTypeface(typeface);
        txtManager.setTypeface(typeface);
        txtPhone.setTypeface(typeface);
        txtAtelierAbout.setTypeface(typeface);
        txtAtelierAboutTitle.setTypeface(typeface);
        btnMapAddress.setTypeface(typeface);

        int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
        GradientDrawable gradientDrawable = (GradientDrawable) btnMapAddress.getBackground();
        gradientDrawable.setStroke(2, colorId);
        btnMapAddress.setTextColor(colorId);
        btnMapAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityAtelier.this,ActivityMap.class);
                startActivity(intent);
            }
        });

    }
}
