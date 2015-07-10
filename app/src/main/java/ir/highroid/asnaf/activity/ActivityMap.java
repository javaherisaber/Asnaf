package ir.highroid.asnaf.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ir.highroid.asnaf.R;

public class ActivityMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            int colorId = ContextCompat.getColor(getApplicationContext(),R.color.colorCustomize);
            getWindow().setNavigationBarColor(colorId);
            getWindow().setStatusBarColor(colorId);
        }

        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in photographer and move the camera
        LatLng hotographer = new LatLng(Double.parseDouble(getResources().getString(R.string.map_latitude_Photographer))
                ,Double.parseDouble(getResources().getString(R.string.map_longitude_Photographer)));
        mMap.addMarker(new MarkerOptions().position(hotographer).title(getResources().getString(R.string.map_marker_name_Photographer)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hotographer));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(hotographer)
                .zoom(Float.parseFloat(
                        getResources().getString(R.string.map_zoom_level_Photographer)
                )).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }
}
