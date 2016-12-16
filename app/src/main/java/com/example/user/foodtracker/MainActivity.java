package com.example.user.foodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.contact_list);

        final DatabaseHandler db = ((MainApplication)getApplication()).db;

        db.addContact(new FoodDiary("cereal", "breakfast"));
        db.addContact(new FoodDiary("pizza", "dinner"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getAllContacts(db));
        mListView.setAdapter(adapter);

    }

    private ArrayList<String> getAllContacts(DatabaseHandler db) {
        ArrayList<String> contactNames = new ArrayList<String>();

        ArrayList<FoodDiary> contacts = db.getAllContacts();
        for (FoodDiary contact : contacts) {
            contactNames.add(contact.getName() + " for " + contact.getPhone_number());

        }
        return contactNames;
    }
}
