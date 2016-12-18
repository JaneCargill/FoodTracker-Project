package com.example.user.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/12/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "FoodTracker";

    private static final String TABLE_FOODINFO = "Food_Entries";
    private static final String KEY_ID = "id";
    private static final String KEY_MONTH = "month";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_MEAL = "meal";
    private static final String KEY_FOOD_EATEN = "food_eaten";
    private static final String KEY_KCAL = "kcal";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FOODINFO + " ("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MONTH + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_TIME + " TEXT,"
                + KEY_MEAL + " TEXT," + KEY_FOOD_EATEN + " TEXT," + KEY_KCAL + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODINFO);
        onCreate(db);
    }

    private void runSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void addFoodEntry(FoodDiary foodEntry) {
        String month = foodEntry.getMonth();
        String date = foodEntry.getDate();
        String time = foodEntry.getTime();
        String meal = foodEntry.getMeal();
        String food_eaten = foodEntry.getFoodEaten();
        int kcal = foodEntry.getKcal();


        String sql = "INSERT INTO " + TABLE_FOODINFO +
                "(" + KEY_MONTH + "," + KEY_DATE + "," + KEY_TIME + "," + KEY_MEAL + "," + KEY_FOOD_EATEN + "," + KEY_KCAL + " ) VALUES ('"
                + month + "','" + date + "', '" + time + "', '" + meal + "','" + food_eaten + "'," + Integer.toString(kcal) + ")";
        runSQL(sql);
    }


//    // Getting single entry
//    public FoodDiary getFoodEntry(int id) {
//        String sql = "SELECT * FROM " + TABLE_FOODINFO + " WHERE " + KEY_ID + " = " + id;
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//
//            FoodDiary food_entry = getFoodFromDBCursor(cursor);
//            return food_entry;
//        }
//        return null;
//    }
//
//    public FoodDiary getFoodEntryByMonth(String month) {
//        String sql = "SELECT * FROM " + TABLE_FOODINFO + " WHERE " + KEY_MONTH + " = '" + month + "'";
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//
//            FoodDiary food_entry = getFoodFromDBCursor(cursor);
//            return food_entry;
//        }
//        return null;
//    }
//
//    public FoodDiary getFoodEntryByMeal(String meal) {
//        String sql = "SELECT * FROM " + TABLE_FOODINFO + " WHERE " + KEY_MEAL + " = '" + meal + "'";
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//
//            FoodDiary food_entry = getFoodFromDBCursor(cursor);
//            return food_entry;
//        }
//        return null;
//    }

            // Getting All FoodEntries
            public ArrayList<FoodDiary> getAllFoodEntries() {
                ArrayList<FoodDiary> foodEntries = new ArrayList<FoodDiary>();

                String sql = "SELECT * FROM " + TABLE_FOODINFO;

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(sql, null);

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
//                FoodDiary contact = new FoodDiary();
//                contact.setID(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhone_number(cursor.getString(2));
                        FoodDiary food_entry = getFoodFromDBCursor(cursor);
                        // Adding contact to list
                        foodEntries.add(food_entry);
                    } while (cursor.moveToNext());
                }

                // return contact list
                return foodEntries;
            }



//             Getting contacts Count
//    public int getContactsCount() {}
//
//             Updating single contact
//    public int updateContact(Contact contact) {}
//
//             Deleting single contact
        public void deleteFoodEntry (FoodDiary food_entry){
            int id = food_entry.getID();

            String sql = "DELETE FROM " + TABLE_FOODINFO + " WHERE " + KEY_ID + " = " + id;
            runSQL(sql);
        }

        public void deleteFoodEntry(int id) {
            String sql = "DELETE FROM " + TABLE_FOODINFO + " WHERE " + KEY_ID + " = " + id;
            runSQL(sql);
    }

        public void deleteAllFood() {
            String sql = "DELETE FROM " + TABLE_FOODINFO;
            runSQL(sql);
    }


        private FoodDiary getFoodFromDBCursor(Cursor cursor) {

            // Get the column index for each column in the table
            int idColumnNum = cursor.getColumnIndex(KEY_ID);
            int foodEatenColumnNum = cursor.getColumnIndex(KEY_FOOD_EATEN);
            int mealColumnNum = cursor.getColumnIndex(KEY_MEAL);
            int monthColumnNum = cursor.getColumnIndex(KEY_MONTH);
            int dateColumnNum = cursor.getColumnIndex(KEY_DATE);
            int timeColumnNum = cursor.getColumnIndex(KEY_TIME);
            int kcalColumnNum = cursor.getColumnIndex(KEY_KCAL);

            // get the data in the fields at each column
            int id = Integer.parseInt(cursor.getString(idColumnNum));
            String foodEaten = cursor.getString(foodEatenColumnNum);
            String meal = cursor.getString(mealColumnNum);
            String month = cursor.getString(monthColumnNum);
            String date = cursor.getString(dateColumnNum);
            String time = cursor.getString(timeColumnNum);
            int kcal = Integer.parseInt(cursor.getString(kcalColumnNum));

            FoodDiary foodEntry = new FoodDiary(id, month, date, time, meal, foodEaten, kcal);
        /*dartPlayer.setId(id);
        dartPlayer.setName(name);
        dartPlayer.setNickname(nickname);
        dartPlayer.setRanking(ranking);*/

            return foodEntry;
        }

}
