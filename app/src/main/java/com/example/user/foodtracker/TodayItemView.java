package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 20/12/2016.
 */

public class TodayItemView extends AppCompatActivity {

    ListView mListView;
    ArrayAdapter<String> listAdapter;
    TextView todayDate;
    TextView totalCals;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todays_activity);

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();

        final DatabaseHandler db = ((MainApplication) getApplication()).db;

        todayDate = (TextView) findViewById(R.id.today_date);
        mListView = (ListView) findViewById(R.id.food_entries);
        listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getAllTodaysFood(db));
        totalCals = (TextView)findViewById(R.id.total_cals);
        mListView.setAdapter(listAdapter);
        String today = db.getTodaysDate();
        Log.d("Todays date: ", today);
        todayDate.setText(today);

        Integer totalKcal = db.getKcalsForTodaysFood();
        Log.d("Cals: ", totalKcal.toString());
        totalCals.setText(totalKcal.toString());

//        int count = db.getTodaysFood();
//        todayDate.setText(count);
//    }
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                String selectedEntry = (String) mListView.getItemAtPosition(position);
////                Log.d("item at position: ", selectedEntry);
//
//                FoodDiary foodEntry = db.getFoodEntry(position + 1);
//
//                Intent intent = new Intent(TodayItemView.this, SingleItemView.class);
//
//                intent.putExtra("id", foodEntry.getID());
//                intent.putExtra("month", foodEntry.getMonth());
//                intent.putExtra("date", foodEntry.getDate());
//                intent.putExtra("day", foodEntry.getDay());
//                intent.putExtra("meal", foodEntry.getMeal());
//                intent.putExtra("food", foodEntry.getFoodEaten());
//                intent.putExtra("kcal", foodEntry.getKcal());
//
//                Log.d("WORKING :", intent.getExtras().toString());
//
//                startActivity(intent);
//            }
//        });


    }

    private ArrayList<String> getAllTodaysFood(DatabaseHandler db) {
        ArrayList<String> foodDiary = new ArrayList<String>();

        ArrayList<FoodDiary> foodEntries = db.getTodaysFood();
        for (FoodDiary foodEntry : foodEntries) {
            foodDiary.add(foodEntry.getMeal() + ": " + foodEntry.getFoodEaten() + " on day: " + foodEntry.getDay() + " date: " + foodEntry.getDate() + " month: " + foodEntry.getMonth() + " " + foodEntry.getKcal() + "kcal");

        }
        return foodDiary;
    }
}


