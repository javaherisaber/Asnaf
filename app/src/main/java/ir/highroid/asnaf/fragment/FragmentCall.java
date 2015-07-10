package ir.highroid.asnaf.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.activity.ActivityHome;
import ir.highroid.asnaf.bundle.BundleCall;
import ir.highroid.asnaf.database.DatabaseInteract;
import ir.highroid.asnaf.view.customtabs.CustomTabActivityHelper;

/**
 * Created by mohammad on 6/20/2016.
 */
public class FragmentCall extends Fragment implements View.OnClickListener{

    View rootView;
    TextView txtTitle;
    AppCompatButton btnCall, btnEmail, btnWeb, btnTelegram;
    private Typeface typeface;

    private GoogleMap mMap;
    private BundleCall bundleCall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_call, container, false);
        initObjectAndView();
        initListenerAndEvent();
        initContent();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap();
    }

    private void initObjectAndView(){
        txtTitle = (TextView) rootView.findViewById(R.id.txtTitle);
        btnCall = (AppCompatButton) rootView.findViewById(R.id.btnCall);
        btnEmail = (AppCompatButton) rootView.findViewById(R.id.btnEmail);
        btnWeb = (AppCompatButton) rootView.findViewById(R.id.btnWeb);
        btnTelegram = (AppCompatButton) rootView.findViewById(R.id.btnTelegram);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "IRAN Sans.ttf");

        txtTitle.setTypeface(typeface);
        btnCall.setTypeface(typeface);
    }

    private void initContent(){
        DatabaseInteract db = new DatabaseInteract(getContext());
        boolean isHome = ((ActivityHome)getActivity()).isHome;
        if(isHome){
            bundleCall = db.getCallInfo(1);
            setupBtns(bundleCall);
        }else {
            bundleCall = db.getCallInfo(33);
            setupBtns(bundleCall);
        }
    }

    private void setupBtns(BundleCall bundleCall){
        btnCall.setText(bundleCall.phone);
        String webUrl = bundleCall.webUrl;
        webUrl = "www." + webUrl.substring(webUrl.lastIndexOf("/")+1);
        btnWeb.setText(webUrl);
        String telegram = bundleCall.telegram;
        telegram = "@" + telegram.substring(telegram.lastIndexOf("/")+1);
        btnTelegram.setText(telegram);
    }

    private void initMap(){

        if(isGooglePlayServicesAvailable(getActivity()))
        {
            SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;

                    // Add a marker in Photographer and move the camera
                    LatLng Photographer = new LatLng(Double.parseDouble(bundleCall.latitude)
                            ,Double.parseDouble(bundleCall.longitude));
                    mMap.addMarker(new MarkerOptions().position(Photographer).title(bundleCall.marker));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Photographer));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(Photographer)
                            .zoom(Float.parseFloat(
                                    getResources().getString(R.string.map_zoom_level_Photographer)
                            )).build();
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                    mMap.moveCamera(cameraUpdate);
                }
            });
        }
    }

    private void initListenerAndEvent(){
        btnCall.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnTelegram.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCall:
                String uri = "tel:" + bundleCall.phone ;
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse(uri));
                startActivity(intent1);
                break;

            case R.id.btnEmail:
                String recepientEmail = "info@example.ir"; // either set to destination email or leave empty
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + recepientEmail));
                intent.putExtra(Intent.EXTRA_SUBJECT, "پیام به example");
                startActivity(intent);
                break;

            case R.id.btnWeb:
                CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                CustomTabActivityHelper.openCustomTab(getActivity(), customTabsIntent, Uri.parse(bundleCall.webUrl),
                        new CustomTabActivityHelper.CustomTabFallback() {
                            @Override
                            public void openUri(Activity activity, Uri uri) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        });
                break;

            case R.id.btnTelegram:
                CustomTabsIntent customTabsIntent1 = new CustomTabsIntent.Builder().build();
                CustomTabActivityHelper.openCustomTab(getActivity(), customTabsIntent1, Uri.parse(bundleCall.telegram),
                        new CustomTabActivityHelper.CustomTabFallback() {
                            @Override
                            public void openUri(Activity activity, Uri uri) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        });
                break;
        }
    }

    public boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if(status != ConnectionResult.SUCCESS) {
//            if(googleApiAvailability.isUserResolvableError(status)) {
//                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
//            }
            return false;
        }
        return true;
    }
}
