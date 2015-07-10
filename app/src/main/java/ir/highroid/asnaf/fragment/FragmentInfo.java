package ir.highroid.asnaf.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.activity.ActivityHome;
import ir.highroid.asnaf.database.DatabaseInteract;

/**
 * Created by mohammad on 6/20/2016.
 */
public class FragmentInfo extends Fragment {

    View rootView;
    TextView txtTitle, txtDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_info, container, false);

        txtTitle = (TextView) rootView.findViewById(R.id.txtTitle);
        txtDescription = (TextView) rootView.findViewById(R.id.txtDescription);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "IRAN Sans.ttf");

        txtTitle.setTypeface(typeface);
        txtDescription.setTypeface(typeface);

        DatabaseInteract db = new DatabaseInteract(getContext());
        boolean isHome = ((ActivityHome)getActivity()).isHome;
        String title;
        if(isHome){
            title = db.getUnionDes(1);
        }else {
            title = db.getUnionDes(33);
        }
        txtTitle.setText(title);
        txtDescription.setText("توضیحی درباره ی " + title);
        return rootView;
    }
}
