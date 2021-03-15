package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;





import java.util.ArrayList;


/**
 * MainActivity Class (For Main Title Screen Page)
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int ERROR_DIALOG_REQUEST = 9001;

    Button search_time_location;
    Button rank_time;
    Button safest_path;

    /**
     * Initialize all public and private variables
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!DataStructures.statesInitialized) {
            DataStructures.states = Input.getState(getResources().openRawResource(R.raw.modifieddata1));
            DataStructures.statesInitialized = true;
        }
        if(!DataStructures.graphInitialized) {
            DataStructures.graph = Input.getGraph(getResources().openRawResource(R.raw.state_adjacency), DataStructures.states);
            DataStructures.graphInitialized = true;
        }
        int[] arr = {R.drawable.cold, R.drawable.flood, R.drawable.fog};
        setContentView(R.layout.intro_screen);

        if(!DataStructures.stateNamesInitialized) {
            DataStructures.stateNames = new ArrayList<String>();
            DataStructures.stateNamesInitialized = true;
        }
        for(State s : DataStructures.states){
            DataStructures.stateNames.add(s.toString());
        }
        Input.getStatePosition(getResources().openRawResource(R.raw.states_position), DataStructures.states);

        if(!DataStructures.weatherGroupingInitialized){
            DataStructures.weatherGrouping = Input.getCategory(getResources().openRawResource(R.raw.events_sorted));
            DataStructures.weatherGroupingInitialized = true;
        }

        search_time_location = findViewById(R.id.button_search);
        rank_time = findViewById(R.id.button_rank_time);
        safest_path = findViewById(R.id.button_safest);

        search_time_location.setOnClickListener(this);
        rank_time.setOnClickListener(this);
        safest_path.setOnClickListener(this);
    }

    /**
     * Determines activity based on what button has been clicked
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_search:
                Intent openSearchMapActivity = new Intent(this, SearchMapActivity.class);
                startActivity(openSearchMapActivity);
                break;
            case R.id.button_rank_time:
                Intent openRankTimeActivity = new Intent(this, RankTimeActivity.class);
                startActivity(openRankTimeActivity);
                break;
            case R.id.button_safest:
                Intent openSafestPathActivity = new Intent(this, SafestPathActivity.class);
                startActivity(openSafestPathActivity);
                break;

        }
    }
}
