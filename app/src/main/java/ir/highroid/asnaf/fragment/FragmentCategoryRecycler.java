package ir.highroid.asnaf.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.activity.ActivityHome;
import ir.highroid.asnaf.adapter.AdapterCategoryRecycler;
import ir.highroid.asnaf.bundle.BundleCategory;

/**
 * Created by mohammad on 6/19/2016.
 */
public class FragmentCategoryRecycler extends Fragment {

    View rootView;
    private RecyclerView categoryRecycler;
    private LinearLayoutManager linearLayoutManager;
    private SliderLayout sliderShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_category_recycler, container, false);
        initObjectAndViews();
        initNewsRecycler();
        initSlider();
        return rootView;
    }

    private void initNewsRecycler(){
        List<BundleCategory> categoryList = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            BundleCategory bundleCategory = new BundleCategory();
            bundleCategory.title = "عکاسی کانن";
            if(((ActivityHome)getActivity()).isHome){
                bundleCategory.type = "home";
            }
            else {
                bundleCategory.type = "notHome";
            }
            bundleCategory.image = "c" + i;
            categoryList.add(bundleCategory);
        }
        AdapterCategoryRecycler adapterNewsRecycler = new AdapterCategoryRecycler(categoryList, getActivity().getApplicationContext());
        categoryRecycler.setLayoutManager(linearLayoutManager);
        categoryRecycler.setAdapter(adapterNewsRecycler);

    }

    private void initObjectAndViews(){
        categoryRecycler = (RecyclerView) rootView.findViewById(R.id.categoryRecycler);
        categoryRecycler.setNestedScrollingEnabled(false);
        sliderShow = (SliderLayout) rootView.findViewById(R.id.slider);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    private void initSlider(){
        HashMap<String,Integer> file_maps = new HashMap<>();
        file_maps.put("Hannibal",R.drawable.slide1);
        file_maps.put("Big Bang Theory",R.drawable.slide2);

        for(String name : file_maps.keySet()){
            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
            defaultSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
            sliderShow.addSlider(defaultSliderView);
        }
    }

    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

}
