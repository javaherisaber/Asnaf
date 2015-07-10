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
import ir.highroid.asnaf.adapter.AdapterGalleryRecycler;
import ir.highroid.asnaf.adapter.AdapterUnionRecycler;
import ir.highroid.asnaf.bundle.BundleGallery;
import ir.highroid.asnaf.bundle.BundleUnion;
import ir.highroid.asnaf.database.DatabaseInteract;

/**
 * Created by mohammad on 6/29/2016.
 */
public class FragmentUnionMain extends Fragment {
    View rootView;
    private RecyclerView unionRecycler;
    private LinearLayoutManager linearLayoutManager;
    private SliderLayout sliderShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_union, container, false);
        initObjectAndViews();
        initNewsRecycler();
        initSlider();
        return rootView;
    }

    private void initNewsRecycler(){
        List<BundleUnion> unionList;
        DatabaseInteract databaseInteract = new DatabaseInteract(getActivity().getApplicationContext());
        unionList = databaseInteract.getUnions();
        AdapterUnionRecycler adapterUnionRecycler = new AdapterUnionRecycler(unionList, getActivity().getApplicationContext());
        unionRecycler.setLayoutManager(linearLayoutManager);
        unionRecycler.setAdapter(adapterUnionRecycler);
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

    private void initObjectAndViews(){
        unionRecycler = (RecyclerView) rootView.findViewById(R.id.unionRecycler);
        unionRecycler.setNestedScrollingEnabled(false);
        sliderShow = (SliderLayout) rootView.findViewById(R.id.slider);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }
}
