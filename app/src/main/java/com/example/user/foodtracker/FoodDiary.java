package com.example.user.foodtracker;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

/**
 * Created by user on 16/12/2016.
 */

public class FoodDiary {

    private int id;
    private String food_eaten;
    private String meal;
    private String month;
    private String date;
    private String day;
    private int kcal;


    public FoodDiary(int id, String month, String date, String day, String meal, String food_eaten, int kcal) {
        this.id = id;
        this.food_eaten = food_eaten;
        this.meal = meal;
        this.month = month;
        this.date = date;
        this.day = day;
        this.kcal = kcal;
    }
//    public FoodDiary(String month, String date, String meal, String food_eaten, int kcal) {
//        this.food_eaten = food_eaten;
//        this.meal = meal;
//        this.month = month;
//        this.date = date;
//        this.kcal = kcal;
//    }
    public FoodDiary(String month, String date, String day, String meal, String food_eaten, int kcal) {
        this.food_eaten = food_eaten;
        this.meal = meal;
        this.month = month;
        this.date = date;
        this.day = day;
        this.kcal = kcal;
//        Log.d("Date:", this.date);
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getFoodEaten() {
        return this.food_eaten;
    }

    public void setFoodEaten(String food_eaten) {
        this.food_eaten = food_eaten;
    }

    public String getMeal() {
        return this.meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() { return this.date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) { this.day = day; }

    public int getKcal() { return this.kcal; }

    public void setKcal(int kcal) { this.kcal = kcal; }
}
