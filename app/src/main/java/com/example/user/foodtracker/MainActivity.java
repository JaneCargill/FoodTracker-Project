package com.example.user.foodtracker;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    ArrayAdapter<String> listAdapter;
    EditText editSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHandler db = ((MainApplication)getApplication()).db;

        mListView = (ListView)findViewById(R.id.food_entries);
        editSearch = (EditText) findViewById(R.id.search);
        listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getAllFoodEntries(db));

//                db.deleteAllFood();
//        db.addFoodEntry(new FoodDiary("Dec", "17th", "7pm", "Dinner", "pizza", 500));
//        db.addFoodEntry(new FoodDiary("Dec", "17th", "8am", "Breakfast", "cereal", 200));
//        db.addFoodEntry(new FoodDiary("Dec", "17th", "7pm", "Lunch", "salad", 100));


        mListView.setAdapter(listAdapter);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedEntry = (String) mListView.getItemAtPosition(position);
//                Log.d("item at position: ", selectedEntry);
                FoodDiary foodEntry = db.getFoodEntry(position + 1);

                Intent intent = new Intent(MainActivity.this, SingleItemView.class);

                intent.putExtra("id", foodEntry.getID());
                intent.putExtra("month", foodEntry.getMonth());
                intent.putExtra("date", foodEntry.getDate());
                intent.putExtra("time", foodEntry.getTime());
                intent.putExtra("meal", foodEntry.getMeal());
                intent.putExtra("food", foodEntry.getFoodEaten());
                intent.putExtra("kcal", foodEntry.getKcal());

                Log.d("WORKING :", intent.getExtras().toString());

                startActivity(intent);
            }
        });
    }



    private ArrayList<String> getAllFoodEntries(DatabaseHandler db) {
        ArrayList<String> foodDiary = new ArrayList<String>();

        ArrayList<FoodDiary> foodEntries = db.getAllFoodEntries();
        for (FoodDiary foodEntry : foodEntries) {
            foodDiary.add(foodEntry.getMeal() + ": " + foodEntry.getFoodEaten() + " on " + foodEntry.getMonth() + " " + foodEntry.getDate() + " " + foodEntry.getKcal() + "kcal");

        }
        return foodDiary;
    }
}
