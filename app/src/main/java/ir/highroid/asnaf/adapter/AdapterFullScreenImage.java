package ir.highroid.asnaf.adapter;

import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.view.TouchImageView;

public class AdapterFullScreenImage extends PagerAdapter {
 
    private Activity activity;
    private ArrayList<String> imageList;
    private LayoutInflater layoutInflater;
 
    // constructor
    public AdapterFullScreenImage(Activity activity, ArrayList<String> imageList) {
        this.activity = activity;
        this.imageList = imageList;
    }
 
    @Override
    public int getCount() {
        return this.imageList.size();
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
     
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TouchImageView imgDisplay;

        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = layoutInflater.inflate(R.layout.layout_fullscreen_image, container, false);
        imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);
        String imageRes = imageList.get(position);
        int id = activity.getResources().getIdentifier(imageRes, "drawable", activity.getPackageName());
        imgDisplay.setImageResource(id);
        ((ViewPager) container).addView(viewLayout);
  
        return viewLayout;
    }
     
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}