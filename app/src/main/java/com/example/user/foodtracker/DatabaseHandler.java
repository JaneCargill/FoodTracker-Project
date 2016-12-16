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

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "contactsManager";

    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    private void runSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void addContact(FoodDiary food) {
        String name = food.getName();
        String number = food.getPhone_number();

        String sql = "INSERT INTO " + TABLE_CONTACTS +
                "(" + KEY_NAME + "," + KEY_PH_NO + " ) VALUES ('"
                + name + "','" + number + "')";
        runSQL(sql);
    }


    // Getting single contact
    public FoodDiary getContact(int id) {
        String sql = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + KEY_ID + " = " + id;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();

            FoodDiary contact = getFoodFromDBCursor(cursor);
            return contact;
        }
        return null;
    }

    public FoodDiary getContact(String name) {
        String sql = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + KEY_NAME + " = '" + name + "'";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();

            FoodDiary contact = getFoodFromDBCursor(cursor);
            return contact;
        }
        return null;
    }

            // Getting All Contacts
            public ArrayList<FoodDiary> getAllContacts() {
                ArrayList<FoodDiary> contactList = new ArrayList<FoodDiary>();

                String sql = "SELECT  * FROM " + TABLE_CONTACTS;

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(sql, null);

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
//                FoodDiary contact = new FoodDiary();
//                contact.setID(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhone_number(cursor.getString(2));
                        FoodDiary contact = getFoodFromDBCursor(cursor);
                        // Adding contact to list
                        contactList.add(contact);
                    } while (cursor.moveToNext());
                }

                // return contact list
                return contactList;
            }



            // Getting contacts Count
//    public int getContactsCount() {}

            // Updating single contact
//    public int updateContact(Contact contact) {}

            // Deleting single contact
        public void deleteContact (FoodDiary contact){
            int id = contact.getID();

            String sql = "DELETE FROM " + TABLE_CONTACTS + " WHERE " + KEY_ID + " = " + id;
            runSQL(sql);
        }

        public void deletePlayer(int id) {
            String sql = "DELETE FROM " + TABLE_CONTACTS + " WHERE " + KEY_ID + " = " + id;
            runSQL(sql);
    }

        public void deleteAllPlayers() {
            String sql = "DELETE FROM " + TABLE_CONTACTS;
            runSQL(sql);
    }


        private FoodDiary getFoodFromDBCursor(Cursor cursor) {

            // Get the column index for each column in the table
            int idColumnNum = cursor.getColumnIndex(KEY_ID);
            int nameColumNum = cursor.getColumnIndex(KEY_NAME);
            int phonenumColumnNum = cursor.getColumnIndex(KEY_PH_NO);


            // get the data in the fields at each column
            int id = Integer.parseInt(cursor.getString(idColumnNum));
            String name = cursor.getString(nameColumNum);
            String phonenum = cursor.getString(phonenumColumnNum);

            FoodDiary contact = new FoodDiary(id, name, phonenum);
        /*dartPlayer.setId(id);
        dartPlayer.setName(name);
        dartPlayer.setNickname(nickname);
        dartPlayer.setRanking(ranking);*/

            return contact;
        }

}
