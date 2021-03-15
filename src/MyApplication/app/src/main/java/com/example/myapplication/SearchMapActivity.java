package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

/**
 * SearchMapActivity
 * Input Page for Search Map
 */
public class SearchMapActivity extends AppCompatActivity {
    private int current_state;
    private int current_month;

    Button submitButton;
    SpinnerDialog spinnerDialog;
    Button btnSearch;

    TextView Date;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    /**
     * Initializes the activities for this page
     * @param savedInstanceState Saved Instance State
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_time_location_screen);

        // Initializing the elements in the page (1 State Spinner, 1 Date Spinner)
        loadSpinnerDialog();
        loadDateSpinner();

        submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Indicates what happens when submit button is pressed
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MapActivity.class);
                i.putExtra("state", current_state);
                i.putExtra("month", current_month);
                startActivity(i);
            }
        });
    }

    /**
     * on restart, execute onDestroy()
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
        spinnerDialog = new SpinnerDialog(SearchMapActivity.this,DataStructures.stateNames,"Select Item:");

        spinnerDialog.setCancellable(true);
        spinnerDialog.setShowKeyboard(false);

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            /**
             * Show what the spinner says when selection has been made
             * @param v View
             */
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            /**
             * Sets position to to_state and output text to notify user of what was chosen
             * @param item The chosen destination state
             * @param position The position of the chosen state
             */
            @Override
            public void onClick(String item, int position) {
                current_state = position;
                btnSearch.setText(item);
            }
        });
    }

    /**
     * Initializes the Date Spinner
     */
    public void loadDateSpinner(){
        Date = findViewById(R.id.tv_date);

        Date.setOnClickListener(new View.OnClickListener() {
            /**
             * Shows what the date spinner says when selection has been made
             * @param v View
             */
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchMapActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            /**
             * Sets the current month and displays text to notify user of what was chosen
             * @param view View
             * @param year Year chosen
             * @param month Month Chosen
             * @param dayOfMonth Day Chosen
             */
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month+"/"+dayOfMonth+"/"+year;
                current_month = month;
                Date.setText(date);
            }
        };

    }
}
