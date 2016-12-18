package com.example.user.foodtracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    EditText foodInput;
    ListView mListView;
//    Button newFoodButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        foodInput = (EditText)findViewById(R.id.food_input);
        mListView = (ListView)findViewById(R.id.food_entries);
//        newFoodButton = (Button)findViewById(R.id.add_food_button);



        final DatabaseHandler db = ((MainApplication)getApplication()).db;

        db.deleteAllFood();
        db.addFoodEntry(new FoodDiary("Dec", "17th", "8am", "Breakfast", "cereal", 200));
        db.addFoodEntry(new FoodDiary("Dec", "17th", "7pm", "Lunch", "salad", 100));
        db.addFoodEntry(new FoodDiary("Dec", "17th", "7pm", "Dinner", "pizza", 500));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getAllFoodEntries(db));
        mListView.setAdapter(adapter);




//        newFoodButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this, FoodDiaryActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        mListView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String input = foodInput.getText().toString();
//                FoodDiary foodEntry = db.addContact(input);
//
//                Intent intent = new Intent(MainActivity.this, FoodDiaryActivity.class);
//
//                intent.putExtra("name", FoodDiary.getName());
//                intent.putExtra("phoneNumber", FoodDiary.getPhone_number());
//
//                startActivity(intent);
//            }
//        });

    }

    private ArrayList<String> getAllFoodEntries(DatabaseHandler db) {
        ArrayList<String> foodDiary = new ArrayList<String>();

        ArrayList<FoodDiary> foodEntries = db.getAllFoodEntries();
        for (FoodDiary foodEntry : foodEntries) {
            foodDiary.add(foodEntry.getMeal() + ": " + foodEntry.getFoodEaten() + " on " + foodEntry.getMonth() + " " + foodEntry.getDate() + " " + foodEntry.getKcal()+ "kcal");

        }
        return foodDiary;
    }
}
