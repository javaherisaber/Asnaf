package ir.highroid.asnaf.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.activity.ActivityHome;
import ir.highroid.asnaf.activity.ActivityNewsList;
import ir.highroid.asnaf.activity.ActivityRuleList;
import ir.highroid.asnaf.adapter.AdapterAsnafMemberAdapter;
import ir.highroid.asnaf.bundle.BundleAsnafMember;
import ir.highroid.asnaf.database.DatabaseInteract;

/**
 * Created by mohammad on 6/20/2016.
 */
public class FragmentUnion extends Fragment {

    View rootView;
    TextView txtMember, txtDescription, txtNewsText, txtRuleText;
    CardView btnRules, btnNews;
    private RecyclerView categoryRecycler;
    private LinearLayoutManager linearLayoutManager;
    Typeface typeface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initObjectAndView();
        initContent();
        initAsnafMemberRecycler();
        initListenerAndEvent();
        return rootView;
    }

    private void initObjectAndView(){

        categoryRecycler = (RecyclerView) rootView.findViewById(R.id.asnafMemberRecycler);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        txtMember = (TextView) rootView.findViewById(R.id.txtMember);
        txtDescription = (TextView) rootView.findViewById(R.id.txtDescription);
        txtNewsText = (TextView) rootView.findViewById(R.id.txtNewsText);
        txtRuleText = (TextView) rootView.findViewById(R.id.txtRuleText);
        btnRules = (CardView) rootView.findViewById(R.id.btnRules);
        btnNews = (CardView) rootView.findViewById(R.id.btnNews);

        typeface = Typeface.createFromAsset(getActivity().getAssets(), "IRAN Sans.ttf");

        txtMember.setTypeface(typeface);
        txtDescription.setTypeface(typeface);
        txtNewsText.setTypeface(typeface);
        txtRuleText.setTypeface(typeface);
    }

    private void initContent(){
        DatabaseInteract db = new DatabaseInteract(getContext());
        boolean isHome = ((ActivityHome)getActivity()).isHome;
        String directorateTitle ;
        if(isHome){
            directorateTitle = db.getDirectorateTitle(1);
        }
        else {
            directorateTitle = db.getDirectorateTitle(33);
            btnNews.setVisibility(View.GONE);
            btnRules.setVisibility(View.GONE);
        }
        txtMember.setText(" هیئت مدیره ی " + directorateTitle);
    }

    private void initListenerAndEvent(){
        btnRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ActivityRuleList.class);
                startActivity(intent);
            }
        });

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ActivityNewsList.class);
                startActivity(intent);
            }
        });
    }

    private void initAsnafMemberRecycler(){
        ArrayList<BundleAsnafMember> asnafMemberList;
        DatabaseInteract db = new DatabaseInteract(getContext());
        boolean isHome = ((ActivityHome)getActivity()).isHome;
        if(isHome){
            asnafMemberList = db.getDirManagers(1);
        }
        else {
            asnafMemberList = db.getDirManagers(33);
        }

        AdapterAsnafMemberAdapter adapterNewsRecycler = new AdapterAsnafMemberAdapter(asnafMemberList, getActivity().getApplicationContext());
        categoryRecycler.setLayoutManager(linearLayoutManager);
        categoryRecycler.setAdapter(adapterNewsRecycler);

    }

}
