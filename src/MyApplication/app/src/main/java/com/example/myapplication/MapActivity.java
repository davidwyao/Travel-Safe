package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MapActivity class
 * Builds map and draws markers on Map API
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LatLngBounds bounds;
    private LatLngBounds.Builder builder;
    private GoogleMap mMap;
    private int current_month, current_state;
    private HashMap<String, Integer> idmap;

    /**
     * Initializes the activities for this page
     * @param savedInstanceState Saved Instance State
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current_month = getIntent().getExtras().getInt("month");
        current_state = getIntent().getExtras().getInt("state");
        loadMap();

        idmap = new HashMap<String, Integer>();
        loadIdMap();
    }

    /**
     * Loads idmap with ids of .bmp for markers
     */
    public void loadIdMap(){
        idmap.put("flood",R.drawable.flood);
        idmap.put("wind",R.drawable.wind);
        idmap.put("tornado",R.drawable.tornado);
        idmap.put("cold",R.drawable.cold);
        idmap.put("extreme_cold",R.drawable.extreme_cold);
        idmap.put("wave",R.drawable.wave);
        idmap.put("heat",R.drawable.heat);
        idmap.put("duststorm",R.drawable.duststorm);
        idmap.put("rain_lightning",R.drawable.rain_lightning);
        idmap.put("hurricane",R.drawable.hurricane);
        idmap.put("smokey",R.drawable.smokey);
        idmap.put("landslide",R.drawable.landslide);
        idmap.put("fog",R.drawable.fog);

    }

    /**
     * Loads Map and layout of page
     */
    public void loadMap(){
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    /**
     * Draw marker Function based on the weather onto position
     * @param point Latitude and Longitude of point
     * @param text The name of the marker (weather event)
     */
    private void drawMarker(LatLng point, String text) {
        for (String category : DataStructures.weatherGrouping.keySet()){
            if (DataStructures.weatherGrouping.get(category).contains(text)){
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(point).title(text).icon(BitmapDescriptorFactory.fromResource(idmap.get(category)));
                mMap.addMarker(markerOptions);
                builder.include(markerOptions.getPosition());
            }
        }
    }

    /**
     * Main Map Function (draws markers by calling function, animates camera to zoom in on state)
     * @param googleMap Google Map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Toast.makeText(this,"Loaded map",Toast.LENGTH_SHORT).show();
        builder = new LatLngBounds.Builder();
        ArrayList<Hazard> haz = DataStructures.states.get(current_state).searchByMonth(current_month);
        if(haz!=null) {
            for (Hazard h : haz) {
                if (!h.lat().equals("N/A") && !h.lon().equals("N/A"))
                    drawMarker(new LatLng(Double.parseDouble(h.lat()), Double.parseDouble(h.lon())), h.name());
            }
            try {
                bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 0);
                mMap.animateCamera(cu);
            }catch(IllegalStateException e){
                State current = DataStructures.states.get(current_state);
                CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(current.lat(), current.lon()), 4);
                mMap.animateCamera(cu);
                Toast.makeText(this, "0 Hazards found for that month", Toast.LENGTH_LONG).show();
            }

        }
        else{
            State current = DataStructures.states.get(current_state);
            CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(current.lat(), current.lon()), 4);
            mMap.animateCamera(cu);
            Toast.makeText(this, "0 Hazards found for that month", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Creates a menu in the top right corner
     * @param menu Menu
     * @return True after all everything has been done
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar, menu);
        return true;
    }

    /**
     * Adds functionality to action_reset button, goes to the home screen
     * @param item Menu option
     * @return Return true if action_reset pressed, otherwise invoke superclass
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
