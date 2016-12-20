package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 19/12/2016.
 */

public class EditFoodEntry extends AppCompatActivity {
    EditText monthEditText;
    EditText dateEditText;
    EditText timeEditText;
    EditText mealEditText;
    EditText foodEditText;
    EditText kcalEditText;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler db = ((MainApplication)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_player);

        monthEditText = (EditText) findViewById(R.id.month);
        dateEditText = (EditText) findViewById(R.id.date);
        timeEditText = (EditText) findViewById(R.id.time);
        mealEditText = (EditText) findViewById(R.id.meal);
        foodEditText = (EditText) findViewById(R.id.food);
        kcalEditText = (EditText) findViewById(R.id.kcal);
        saveButton = (Button) findViewById(R.id.save_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String month = extras.getString("month");
        final String date = extras.getString("date");
        final String time = extras.getString("time");
        final String meal = extras.getString("meal");
        final String food = extras.getString("food");
        final int kcal = extras.getInt("kcal");

        monthEditText.setText(month);
        dateEditText.setText(date);
        timeEditText.setText(time);
        mealEditText.setText(meal);
        foodEditText.setText(food);
        kcalEditText.setText(Integer.toString(kcal));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String month = monthEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String time = timeEditText.getText().toString();
                String meal = mealEditText.getText().toString();
                String food = foodEditText.getText().toString();
                int kcal = Integer.parseInt(kcalEditText.getText().toString());

                FoodDiary entryToUpdate = new FoodDiary(id, month, date, time, meal, food, kcal);
                db.updateEntry(entryToUpdate);
                backToFoodEntryView(id, month, date, time, meal, food, kcal);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToFoodEntryView(id, month, date, time, meal, food, kcal);
            }
        });
    }

    private void backToFoodEntryView(int id, String month, String date, String time, String meal, String food, int kcal) {
        Intent intent = new Intent(EditFoodEntry.this, SingleItemView.class);
        intent.putExtra("id", id);
        intent.putExtra("month", month);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("meal", meal);
        intent.putExtra("food", food);
        intent.putExtra("kcal", kcal);
        startActivity(intent);
    }

}
