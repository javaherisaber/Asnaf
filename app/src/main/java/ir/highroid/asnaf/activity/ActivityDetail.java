package ir.highroid.asnaf.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.adapter.AdapterFullScreenImage;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private AdapterFullScreenImage viewPagerAdapter;
    ImageView btnCloseImage, btnPreviousImage, btnNextImage, btnPlayImage;
    TextView txtImageNumber;
    private CountDownTimer x = null;
    FrameLayout frameCounter;
    private int SPLASH_DISPLAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
            getWindow().setNavigationBarColor(colorId);
            getWindow().setStatusBarColor(colorId);
        }

        setContentView(R.layout.activity_detail);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initObjectAndView();
        initViewPager();
        initListenerAndEvent();
    }


    @Override
    public void onUserInteraction(){
        showObjects();
        if (btnPlayImage.getTag().equals(null)||btnPlayImage.getTag().equals("pause")){
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    hideObjects();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }
    private void hideObjects(){
        btnCloseImage.setVisibility(View.INVISIBLE);
        btnPreviousImage.setVisibility(View.INVISIBLE);
        btnNextImage.setVisibility(View.INVISIBLE);
        btnPlayImage.setVisibility(View.INVISIBLE);
        frameCounter.setVisibility(View.INVISIBLE);
    }

    private void showObjects(){
        btnCloseImage.setVisibility(View.VISIBLE);
        btnPreviousImage.setVisibility(View.VISIBLE);
        btnNextImage.setVisibility(View.VISIBLE);
        btnPlayImage.setVisibility(View.VISIBLE);
        frameCounter.setVisibility(View.VISIBLE);
    }

    private void initObjectAndView(){
        viewPager = (ViewPager) findViewById(R.id.pager);
        btnCloseImage = (ImageView) findViewById(R.id.btnCloseImage);
        btnNextImage = (ImageView) findViewById(R.id.btnNextImage);
        btnPreviousImage = (ImageView) findViewById(R.id.btnPreviousImage);
        btnPlayImage = (ImageView) findViewById(R.id.btnPlayImage);
        txtImageNumber = (TextView) findViewById(R.id.txtCounter);
        frameCounter = (FrameLayout) findViewById(R.id.frameCounter);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "IRAN Sans.ttf");
        txtImageNumber.setTypeface(typeface);
    }

    private void initListenerAndEvent(){
        btnCloseImage.setOnClickListener(this);
        btnNextImage.setOnClickListener(this);
        btnPreviousImage.setOnClickListener(this);
        btnPlayImage.setOnClickListener(this);

        btnPlayImage.setTag("play");

        x = new CountDownTimer(100000, 3000) {
            public void onTick(long millisUntilFinished) {
                int currentItemNum = viewPager.getCurrentItem();
                if (currentItemNum != viewPagerAdapter.getCount()-1) {
                    viewPager.setCurrentItem(++currentItemNum);
                    txtImageNumber.setText(String.valueOf(currentItemNum+1));
                }
                else{
                    viewPager.setCurrentItem(0);
                    txtImageNumber.setText(String.valueOf(1));
                }
            }
            public void onFinish() {
                x.start();
            }
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                txtImageNumber.setText(String.valueOf(position+1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPager(){
        ArrayList<String> df = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            df.add("c" + i);
        }
        int currentItem = getIntent().getIntExtra("imageNum", 0);
        viewPagerAdapter = new AdapterFullScreenImage(ActivityDetail.this, df);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(currentItem);
        txtImageNumber.setText(String.valueOf(currentItem+1));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCloseImage:
                ActivityDetail.this.finish();
                break;

            case R.id.btnNextImage:
                int currentItemNum = viewPager.getCurrentItem();
                if (currentItemNum != viewPagerAdapter.getCount()-1) {
                    viewPager.setCurrentItem(++currentItemNum);
                    txtImageNumber.setText(String.valueOf(currentItemNum+1));
                }
                break;

            case R.id.btnPlayImage:
                if (btnPlayImage.getTag().equals(null)||btnPlayImage.getTag().equals("play")){
                    btnPlayImage.setImageResource(R.drawable.img_pause);
                    btnPlayImage.setTag("pause");
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            hideObjects();
                        }
                    }, SPLASH_DISPLAY_LENGTH);

                    x.start();
                }
                else{
                    btnPlayImage.setImageResource(R.drawable.img_play);
                    btnPlayImage.setTag("play");
                    showObjects();
                    x.cancel();
                }
                break;

            case R.id.btnPreviousImage:
                int currentItemNum1 = viewPager.getCurrentItem();
                if(currentItemNum1 != 0) {
                    viewPager.setCurrentItem(--currentItemNum1);
                    txtImageNumber.setText(String.valueOf(currentItemNum1+1));
                }
                break;
        }
    }

}
