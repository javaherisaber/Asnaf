package ir.highroid.asnaf.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ir.highroid.asnaf.R;

public class ActivityGallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
            getWindow().setNavigationBarColor(colorId);
            getWindow().setStatusBarColor(colorId);
        }

        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
