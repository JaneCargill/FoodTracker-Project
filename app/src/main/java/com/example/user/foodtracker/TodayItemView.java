package com.example.user.foodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todays_activity);

        final DatabaseHandler db = ((MainApplication) getApplication()).db;

        mListView = (ListView) findViewById(R.id.food_entries);
//        listAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, getAllTodaysFood(db));
        todayDate = (TextView)findViewById(R.id.today_date);

        mListView.setAdapter(listAdapter);
        String today = db.getTodaysDate();
        todayDate.setText(today);

//        int count = db.getTodaysFood();
//        todayDate.setText(count);
    }


//    private ArrayList<String> getAllTodaysFood(DatabaseHandler db) {
//        ArrayList<String> foodDiary = new ArrayList<String>();
//
//        Date foodEntries = db.getTodaysFood();
////        for (FoodDiary foodEntry : foodEntries) {
////            foodDiary.add(foodEntry.getMeal() + ": " + foodEntry.getFoodEaten() + " on " + foodEntry.getMonth() + " " + foodEntry.getDate() + " " + foodEntry.getKcal() + "kcal");
////
////        }
//        foodDiary.add(foodEntries.toString());
//        return foodDiary;
//    }
}
