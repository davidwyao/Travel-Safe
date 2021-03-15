package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

/**
 * RankTimeActivity Class
 * Input Page for ranking top 3 months to visit for specific state
 */
public class RankTimeActivity extends AppCompatActivity {
    private int state;

    Button submitButton;
    SpinnerDialog spinnerDialog;
    Button btnSearch;

    /**
     * Initializes the activities for this page
     * @param savedInstanceState Saved Instance State
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_time_screen);
        loadSpinnerDialog();

        submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Indicates what happens when submit button is pressed
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), RankDisplayActivity.class);
                i.putExtra("state", state);
                startActivity(i);
            }
        });
    }

    /**
     * if this activity is restarted, it means we the inner reset was pressed from the inner
     * acitivity which means, this activity should be finished so we go to the main screen
     */
    @Override
    protected void onRestart(){
        super.onRestart();
        finish();
    }

    /**
     * Initializes Spinner for Location
     */
    public void loadSpinnerDialog(){
        spinnerDialog = new SpinnerDialog(RankTimeActivity.this,DataStructures.stateNames,"Select Item:");

        spinnerDialog.setCancellable(true);
        spinnerDialog.setShowKeyboard(false);

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            /**
             * Show what was selected on the spinner to the user
             * @param v View
             */
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            /**
             * Set position to state and output the state chosen so the user is notified
             * @param item State chosen
             * @param position Position of state as integer
             */
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(RankTimeActivity.this, "Selected: " + item, Toast.LENGTH_SHORT).show();
                state = position;
                btnSearch.setText(item);
            }
        });
    }
}
