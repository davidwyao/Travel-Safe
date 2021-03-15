package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * RankDisplayActivity class
 * Displays the ranking of the top 3 months to visit according to State chosen
 */
public class RankDisplayActivity  extends AppCompatActivity {

    private TextView choice1, choice2, choice3;

    /**
     * Main Function that outputs the top 3 months to visit according to State chosen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_results);
        int current_state = getIntent().getExtras().getInt("state");
        ArrayList<Integer> res = DataStructures.states.get(current_state).getTop3();
        choice1  = findViewById(R.id.month1);
        choice1.setText(new DateFormatSymbols().getMonths()[res.get(0)-1]);
        choice2  = findViewById(R.id.month2);
        choice2.setText(new DateFormatSymbols().getMonths()[res.get(1)-1]);
        choice3  = findViewById(R.id.month3);
        choice3.setText(new DateFormatSymbols().getMonths()[res.get(2)-1]);
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
