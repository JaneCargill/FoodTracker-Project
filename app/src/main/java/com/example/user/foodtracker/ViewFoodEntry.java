package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 19/12/2016.
 */

public class ViewFoodEntry extends AppCompatActivity{
    TextView monthEditText;
    TextView dateEditText;
    TextView timeEditText;
    TextView mealEditText;
    TextView food_eatenEditText;
    TextView kcalEditText;
    Button editButton;
    Button deleteButton;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler db = ((MainApplication) getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_player);

        monthEditText = (TextView) findViewById(R.id.month);
        dateEditText = (TextView) findViewById(R.id.date);
        timeEditText = (TextView) findViewById(R.id.time);
        mealEditText = (TextView) findViewById(R.id.meal);
        food_eatenEditText = (TextView) findViewById(R.id.food_input);
        kcalEditText = (TextView) findViewById(R.id.kcal);
        editButton = (Button) findViewById(R.id.edit_button);
        deleteButton = (Button) findViewById(R.id.delete_button);
        backButton = (Button) findViewById(R.id.back_button);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String month = extras.getString("month");
        final String date = extras.getString("date");
        final String time = extras.getString("time");
        final String meal = extras.getString("meal");
        final String food_eaten = extras.getString("food");
        final int kcal = extras.getInt("kcal");

        monthEditText.setText(month);
        dateEditText.setText(date);
        timeEditText.setText(time);
        mealEditText.setText(meal);
        food_eatenEditText.setText(food_eaten);
        kcalEditText.setText(Integer.toString(kcal));

//        editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ViewFoodEntry.this, SingleItemView.class);
//                intent.putExtra("id", id);
//                intent.putExtra("month", month);
//                intent.putExtra("date", date);
//                intent.putExtra("time", time);
//                intent.putExtra("meal", meal);
//                intent.putExtra("food_eaten", food_eaten);
//                intent.putExtra("kcal", kcal);
//                startActivity(intent);
//            }
//        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMainActivity();
            }
        });
    }

        private void backToMainActivity() {
        Intent intent = new Intent(ViewFoodEntry.this, MainActivity.class);
        startActivity(intent);
    }
}

