package ir.highroid.asnaf.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ir.highroid.asnaf.fragment.FragmentUnionMain;
import ir.highroid.asnaf.fragment.FragmentCall;
import ir.highroid.asnaf.fragment.FragmentCategoryRecycler;
import ir.highroid.asnaf.fragment.FragmentUnion;
import ir.highroid.asnaf.fragment.FragmentInfo;

/**
 * Created by mohammad on 6/19/2016.
 */
public class AdapterHomeViewPager extends FragmentStatePagerAdapter {

    boolean isHome;

    public AdapterHomeViewPager(FragmentManager fm, boolean isHome) {
        super(fm);
        this.isHome = isHome;
    }

    @Override
    public Fragment getItem(int position) {


        if(position == 0) {
            if (isHome) {
                return new FragmentUnionMain();
            }
            else{
                return new FragmentCategoryRecycler();
            }
        }
        else if(position == 1){
            return new FragmentUnion();
        }
        else if (position == 2){
            return new FragmentCall();
        }

        return new FragmentInfo();

    }

    @Override
    public int getCount() {
        return 4;
    }

}
