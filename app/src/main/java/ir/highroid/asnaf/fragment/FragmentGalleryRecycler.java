package ir.highroid.asnaf.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.adapter.AdapterGalleryRecycler;
import ir.highroid.asnaf.bundle.BundleGallery;

/**
 * Created by mohammad on 6/19/2016.
 */
public class FragmentGalleryRecycler extends Fragment {
    View rootView;
    private RecyclerView galleryRecycler;
    private GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_gallery_recycler, container, false);
        initObjectAndViews();
        initNewsRecycler();
        return rootView;
    }

    private void initNewsRecycler(){
        List<BundleGallery> galleryList = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            BundleGallery bundleGallery = new BundleGallery();
            bundleGallery.title = "ام دی اف آکریلیک";
            bundleGallery.image = "c"+i;
            galleryList.add(bundleGallery);
        }

        AdapterGalleryRecycler adapterGalleryRecycler = new AdapterGalleryRecycler(galleryList, getActivity().getApplicationContext());
        galleryRecycler.setLayoutManager(gridLayoutManager);
        galleryRecycler.setAdapter(adapterGalleryRecycler);

    }

    private void initObjectAndViews(){
        galleryRecycler = (RecyclerView) rootView.findViewById(R.id.galleryRecycler);
        gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
    }
}
