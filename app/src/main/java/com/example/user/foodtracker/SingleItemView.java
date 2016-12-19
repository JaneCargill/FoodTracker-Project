package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by user on 19/12/2016.
 */

public class SingleItemView extends AppCompatActivity {
    TextView txtmonth;
    TextView txtdate;
    TextView txttime;
    TextView txtmeal;
    TextView txtfood_eaten;
    TextView txtkcal;
    String month;
    String date;
    String time;
    String meal;
    String food_eaten;
    String kcal;

    @Override
    public void onCreate(Bundle savedInsanceState) {
        super.onCreate(savedInsanceState);
        setContentView(R.layout.singleitemview);
        Intent i = getIntent();
        month = i.getStringExtra("month");
        date = i.getStringExtra("date");
        time = i.getStringExtra("time");
        meal = i.getStringExtra("meal");
        food_eaten = i.getStringExtra("food");
        kcal = i.getStringExtra("kcal");

        txtmonth = (TextView)findViewById(R.id.month);
        txtdate = (TextView)findViewById(R.id.date);
        txttime = (TextView)findViewById(R.id.time);
        txtmeal = (TextView)findViewById(R.id.meal);
        txtfood_eaten = (TextView)findViewById(R.id.food_input);
        txtkcal = (TextView)findViewById(R.id.kcal);

        txtmonth.setText(month);
        txtdate.setText(date);
        txttime.setText(time);
        txtmeal.setText(meal);
        txtfood_eaten.setText(food_eaten);
        txtkcal.setText(kcal);
}
}
