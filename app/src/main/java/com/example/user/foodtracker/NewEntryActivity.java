package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Date;

/**
 * Created by user on 18/12/2016.
 */

public class NewEntryActivity extends AppCompatActivity {

    Spinner monthDropdown;
    Spinner dateDropdown;
    Spinner dayDropdown;
    EditText mealInput;
    EditText foodInput;
    EditText kcalInput;
    Button saveButton;
    Button cancelButton;
    String[] months;
    String[] dates;
    String[] days;
    ArrayAdapter<String> monthAdapter;
    ArrayAdapter<String> dateAdapter;
    ArrayAdapter<String> dayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler db = ((MainApplication)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_entry_activity);

        monthDropdown = (Spinner) findViewById(R.id.month_input);
        dateDropdown = (Spinner) findViewById(R.id.date_input);
        dayDropdown = (Spinner) findViewById(R.id.day_input);
        mealInput = (EditText) findViewById(R.id.meal_input);
        foodInput = (EditText) findViewById(R.id.food_input);
        kcalInput = (EditText) findViewById(R.id.kcal_input);
        saveButton = (Button) findViewById(R.id.save_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        dates = new String[]{"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th", "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th", "30th", "31st"};
        days = new String[]{"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        monthAdapter = new ArrayAdapter<String>(NewEntryActivity.this, android.R.layout.simple_spinner_dropdown_item, months);
        dateAdapter = new ArrayAdapter<String>(NewEntryActivity.this, android.R.layout.simple_spinner_dropdown_item, dates);
        dayAdapter = new ArrayAdapter<String>(NewEntryActivity.this, android.R.layout.simple_spinner_dropdown_item, days);

        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthDropdown.setAdapter(monthAdapter);
        dateDropdown.setAdapter(dateAdapter);
        dayDropdown.setAdapter(dayAdapter);

        monthDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(
                    AdapterView<?> adapterView, View view, int pos, long id) {
                 String month = monthDropdown.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected
                    (AdapterView<?> id) {
            }
        });

        dateDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(
                    AdapterView<?> adapterView, View view, int pos, long id) {
                String date = dateDropdown.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected
                    (AdapterView<?> id) {
            }
        });

        dayDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(
                    AdapterView<?> adapterView, View view, int pos, long id) {
                String day = dayDropdown.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected
                    (AdapterView<?> id) {
            }
        });


                saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mnth = monthDropdown.getSelectedItem().toString();
                String dte = dateDropdown.getSelectedItem().toString();
                String dy = dayDropdown.getSelectedItem().toString();
                String meal = mealInput.getText().toString();
                String food = foodInput.getText().toString();
                int kcal = Integer.parseInt(kcalInput.getText().toString());

//                Log.d("get month input: ", month);

                FoodDiary newEntry = new FoodDiary(mnth, dte, dy, meal, food, kcal);
                db.addFoodEntry(newEntry);
                backToMainView();
                    }

        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMainView();
            }
        });
    }

    private void backToMainView() {
        Intent intent = new Intent(NewEntryActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
