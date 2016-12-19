package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 18/12/2016.
 */

public class NewEntryActivity extends AppCompatActivity {

    EditText monthInput;
    EditText dateInput;
    EditText timeInput;
    EditText mealInput;
    EditText foodInput;
    EditText kcalInput;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler db = ((MainApplication)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_entry_activity);

        monthInput = (EditText) findViewById(R.id.month_input);
        dateInput = (EditText) findViewById(R.id.date_input);
        timeInput = (EditText) findViewById(R.id.time_input);
        mealInput = (EditText) findViewById(R.id.meal_input);
        foodInput = (EditText) findViewById(R.id.food_input);
        kcalInput = (EditText) findViewById(R.id.kcal_input);
        saveButton = (Button) findViewById(R.id.save_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String month = monthInput.getText().toString();
                String date = dateInput.getText().toString();
                String time = timeInput.getText().toString();
                String meal = mealInput.getText().toString();
                String food = foodInput.getText().toString();
                int kcal = Integer.parseInt(kcalInput.getText().toString());

                FoodDiary newEntry = new FoodDiary(month, date, time, meal, food, kcal);
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
