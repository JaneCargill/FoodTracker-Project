package com.example.user.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

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
    private static final String KEY_DAY = "day";
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
                + KEY_DATE + " DATE," + KEY_DAY + " TEXT,"
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
        String day = foodEntry.getDay();
        String meal = foodEntry.getMeal();
        String food_eaten = foodEntry.getFoodEaten();
        int kcal = foodEntry.getKcal();


        String sql = "INSERT INTO " + TABLE_FOODINFO +
                "(" + KEY_MONTH + "," + KEY_DATE + "," + KEY_DAY + "," + KEY_MEAL + "," + KEY_FOOD_EATEN + "," + KEY_KCAL + " ) VALUES ('"
                + month + "','" + date + "', '" + day + "', '" + meal + "','" + food_eaten + "'," + Integer.toString(kcal) + ")";
        runSQL(sql);
    }


    // Getting single entry
    public FoodDiary getFoodEntry(int id) {
        String sql = "SELECT * FROM " + TABLE_FOODINFO + " WHERE " + KEY_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();

            FoodDiary food_entry = getFoodFromDBCursor(cursor);
            return food_entry;
        }
        return null;
    }

    public Integer getTotalKcal() {
        String sql = "SELECT SUM(kcal) AS totalKcal FROM " + TABLE_FOODINFO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
//
//            FoodDiary food_entry = getFoodFromDBCursor(cursor);
//            return food_entry;
            Integer total = cursor.getInt(cursor.getColumnIndex("totalKcal"));
            Log.d("Total kcal: ", total.toString());
            return total;

        }
            return null;
    }

//    public FoodDiary getFoodEntry(String id) {
//        String sql = "SELECT * FROM " + TABLE_FOODINFO + " WHERE " + KEY_ID + " = '" + id + "'";
//        Log.d("food entry: ", sql);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        Log.d("FoodEntry: ", cursor.toString());
//        if (cursor != null) {
//            cursor.moveToFirst();
//
//            FoodDiary food_entry = getFoodFromDBCursor(cursor);
//            return food_entry;
//
//        }
//        return null;
//    }
//
//
    public FoodDiary getMonth(String month) {
        String sql = "SELECT * FROM " + TABLE_FOODINFO + " WHERE " + KEY_MONTH + " = '" + month + "'";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();

            FoodDiary food_entry = getFoodFromDBCursor(cursor);
            return food_entry;
        }
        return null;
    }

//    public ArrayList<FoodDiary> getTodaysFood() {
//        ArrayList<FoodDiary> foodEntries = new ArrayList<FoodDiary>();
//
//
//        String sql = "SELECT * FROM " + TABLE_FOODINFO + " WHERE " + KEY_DATE + " = date('now') ";
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        Log.d("Cursor: ", cursor.toString());
//        if (cursor != null) {
//            cursor.moveToFirst();
//
//            FoodDiary food_entry = getFoodFromDBCursor(cursor);
//            Log.d("Date food entry: ", cursor.toString());
//            // Adding contact to list
//            foodEntries.add(food_entry);
//        } while (cursor.moveToNext());
//
//    return foodEntries;
//
//    }

//    public Integer getTodaysFood() {
//        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String sql = "SELECT COUNT(*) from upload_history "
//                + "WHERE strftime('%m%d',file_uploaded_date) = '" + today + "')";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        if(cursor != null && cursor.moveToFirst()){
//            Integer uploadedToday = cursor.getInt(0);
//            Log.d("UploadedToday: ", uploadedToday.toString());
//            return uploadedToday;
//        }
//        return null;
//    }

    public String getTodaysDate() {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        return currentDateTimeString;
    }


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
    public void updateEntry(FoodDiary entry) {
        int id = entry.getID();
        String month = entry.getMonth();
        String date = entry.getDate();
        String day = entry.getDay();
        String meal = entry.getMeal();
        String food = entry.getFoodEaten();
        int kcal = entry.getKcal();

        String sql = "UPDATE " + TABLE_FOODINFO + " SET " + KEY_MONTH + " = '" + month + "', " + KEY_DATE + " = '" + date + "', " + KEY_DAY + " = '" + day + "', " + KEY_MEAL + " = '" + meal + "', " + KEY_FOOD_EATEN + " = '" + food + "', " + KEY_KCAL + " = " + kcal + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);
    }
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
            Integer foodEatenColumnNum = cursor.getColumnIndex(KEY_FOOD_EATEN);
            Log.d("Index: ", foodEatenColumnNum.toString());
            int mealColumnNum = cursor.getColumnIndex(KEY_MEAL);
            int monthColumnNum = cursor.getColumnIndex(KEY_MONTH);
            int dateColumnNum = cursor.getColumnIndex(KEY_DATE);
            int dayColumnNum = cursor.getColumnIndex(KEY_DAY);
            int kcalColumnNum = cursor.getColumnIndex(KEY_KCAL);


                // get the data in the fields at each column
                Integer id = Integer.parseInt(cursor.getString(idColumnNum));
//            Log.d("id: ", id.toString());
                String foodEaten = cursor.getString(foodEatenColumnNum);
//                Log.d("foodEaten: ", foodEaten);
                String meal = cursor.getString(mealColumnNum);
//            Log.d("Meal: ", meal);
                String month = cursor.getString(monthColumnNum);
//            Log.d("Month: ", month);
                String date = cursor.getString(dateColumnNum);
//            Log.d("Date : ", date);
                String day = cursor.getString(dayColumnNum);
//            Log.d("time: ", time);
                Integer kcal = Integer.parseInt(cursor.getString(kcalColumnNum));
//            Log.d("kcal: ", kcal.toString());

                FoodDiary foodEntry = new FoodDiary(id, month, date, day, meal, foodEaten, kcal);



                return foodEntry;
        }

}
