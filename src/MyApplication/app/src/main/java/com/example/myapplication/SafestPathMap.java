package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Stack;

import androidx.appcompat.app.AppCompatActivity;

/**
 * SafestPathMap class
 * Builds Map and Draws Path
 */
public class SafestPathMap  extends AppCompatActivity implements OnMapReadyCallback {

    private LatLngBounds bounds;
    private LatLngBounds.Builder builder;
    private GoogleMap mMap;
    private int current_month;
    private int source_state, target_state;
    private DijkstraSP dj;

    /**
     * Initializes the activities for this page
     * @param savedInstanceState Saved Instance State
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current_month = getIntent().getExtras().getInt("month");
        source_state = getIntent().getExtras().getInt("source_state");
        target_state = getIntent().getExtras().getInt("target_state");
        dj = new DijkstraSP(DataStructures.graph, DataStructures.states, current_month, source_state);

        loadMap();
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
     * Main Map Function (draws markers by calling function, animates camera to zoom in on state)
     * Draws the safestPath based on weight (frequency of hazard)
     * @param googleMap Google Map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Toast.makeText(this,"Loaded map",Toast.LENGTH_SHORT).show();

        Stack<Edge> s = (Stack<Edge>) dj.pathTo(target_state);

        PolylineOptions po = new PolylineOptions();

        if(source_state==target_state){
            State source = DataStructures.states.get(source_state);
            po = po.add(new LatLng(source.lat(),source.lon()));
        }else {
            po = po.add(new LatLng(s.peek().aVert().lat(), s.peek().aVert().lon()));
            while (!s.isEmpty()) {
                Edge e = s.pop();
                Log.e("st", e.otherVert(e.aVert()).lat() + "");
                po = po.add(new LatLng(e.otherVert(e.aVert()).lat(), e.otherVert(e.aVert()).lon()));
            }
        }

        Polyline polyline1 = mMap.addPolyline(po);
        polyline1.setColor(Color.RED);
        polyline1.setWidth(5);

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(39.8283, -98.5795), 4);
        mMap.animateCamera(cu);

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
