package ir.highroid.asnaf.activity;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.database.DatabaseInteract;

public class ActivityNews extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    Typeface tfSans;
    TextView txtNewsContent,txtDate,txtTime;
    private Toolbar toolbar;
    ImageView imgNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initToolbar();
        initObjectAndView();
        initListenersAndEvent();
        initContent();
    }

    private void initObjectAndView(){
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        txtNewsContent =(TextView) findViewById(R.id.txtNewsContent);
        tfSans = Typeface.createFromAsset(getAssets(), "IRAN Sans.ttf");
        imgNews = (ImageView) findViewById(R.id.imgNews);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTime = (TextView) findViewById(R.id.txtTime);

        collapsingToolbarLayout.setCollapsedTitleTypeface(tfSans);
        collapsingToolbarLayout.setExpandedTitleTypeface(tfSans);
        txtNewsContent.setTypeface(tfSans);
        txtDate.setTypeface(tfSans);
        txtTime.setTypeface(tfSans);
    }

    private void initContent(){

        Bundle bundle = getIntent().getBundleExtra("newsInfo");
        imgNews.setImageResource(getImageId(getApplicationContext(),bundle.getString("image")));
        txtDate.setText(bundle.getString("date"));
        txtTime.setText(bundle.getString("time"));
        collapsingToolbarLayout.setTitle(bundle.getString("title"));
        int newsId = bundle.getInt("newsId");
        DatabaseInteract db = new DatabaseInteract(getApplicationContext());
        String des = db.getEventNewsDes(newsId);
        txtNewsContent.setText(des);
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initListenersAndEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNews.this.finish();
            }
        });
    }

    private static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}
