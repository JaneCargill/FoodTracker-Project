package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.id;

/**
 * Created by user on 19/12/2016.
 */

public class SingleItemView extends AppCompatActivity {
    TextView txtmonth;
    TextView txtdate;
    TextView txtday;
    TextView txtmeal;
    TextView txtfood;
    TextView txtkcal;
    Button editButton;
    Button deleteButton;
    Button backButton;


    @Override
    public void onCreate(Bundle savedInsanceState) {
        super.onCreate(savedInsanceState);
        setContentView(R.layout.singleitemview);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String month = extras.getString("month");
        final String date = extras.getString("date");
        final String day = extras.getString("day");
        final String meal = extras.getString("meal");
        final String foodeat = extras.getString("food");
        final int kcal = extras.getInt("kcal");

        final DatabaseHandler db = ((MainApplication)getApplication()).db;


        txtmonth = (TextView) findViewById(R.id.month);
        txtdate = (TextView) findViewById(R.id.date);
        txtday = (TextView) findViewById(R.id.day);
        txtmeal = (TextView) findViewById(R.id.meal);
        txtfood = (TextView) findViewById(R.id.food_eaten);
        txtkcal = (TextView) findViewById(R.id.kcal);
        editButton = (Button) findViewById(R.id.edit_button);
        deleteButton = (Button) findViewById(R.id.delete_button);
        backButton = (Button) findViewById(R.id.back_button);

        txtdate.setText("On " + day + " the " + date + " of " + month + "");
        txtfood.setText("You had " + foodeat + " for " + meal );
        txtkcal.setText("Number of calories consumed: " + kcal );


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleItemView.this, EditFoodEntry.class);
                intent.putExtra("id", id);
                intent.putExtra("month", month);
                intent.putExtra("date", date);
                intent.putExtra("day", day);
                intent.putExtra("meal", meal);
                intent.putExtra("food", foodeat);
                intent.putExtra("kcal", kcal);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteFoodEntry(id);
                Intent intent = new Intent(SingleItemView.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHomePage();
            }
        });
    }



    private void backToHomePage() {
        Intent intent = new Intent(SingleItemView.this, HomePage.class);
        startActivity(intent);
    }
}
