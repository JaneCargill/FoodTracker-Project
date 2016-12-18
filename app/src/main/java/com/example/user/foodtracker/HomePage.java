package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 18/12/2016.
 */

public class HomePage extends AppCompatActivity {

    TextView welcomePage;
    Button viewFoodEntriesButton;
    Button newEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);

        welcomePage = (TextView) findViewById(R.id.welcome_page_text);
        viewFoodEntriesButton = (Button) findViewById(R.id.view_entries_button);
        newEntryButton = (Button)findViewById(R.id.new_entry_button);


        viewFoodEntriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePage.this, NewEntryActivity.class);
                startActivity(intent);

            }
        });
    }
}
