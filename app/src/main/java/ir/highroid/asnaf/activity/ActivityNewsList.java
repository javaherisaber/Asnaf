package ir.highroid.asnaf.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.adapter.AdapterNewsRecycler;
import ir.highroid.asnaf.bundle.BundleNews;
import ir.highroid.asnaf.database.DatabaseInteract;

public class ActivityNewsList extends AppCompatActivity {

    private RecyclerView newsRecycler;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initObjectAndViews();
        initNewsRecycler();
    }

    private void initNewsRecycler(){

        DatabaseInteract db = new DatabaseInteract(getApplicationContext());
        ArrayList<BundleNews> newsList = db.getEventNews(1);

//        for (int i = 0; i < 12; i++) {
//            BundleNews bundleNews = new BundleNews();
//            bundleNews.title = "خبر شماره ی "+ i;
//            newsList.add(bundleNews);
//        }

        AdapterNewsRecycler adapterNewsRecycler = new AdapterNewsRecycler(newsList, getApplicationContext());
        newsRecycler.setLayoutManager(linearLayoutManager);
        newsRecycler.setAdapter(adapterNewsRecycler);

    }

    private void initObjectAndViews(){
        newsRecycler = (RecyclerView) findViewById(R.id.newsRecycler);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
    }
}
