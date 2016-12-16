package com.example.user.foodtracker;

import android.app.Application;

/**
 * Created by user on 16/12/2016.
 */

public class MainApplication extends Application {
    DatabaseHandler db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = new DatabaseHandler(this);
    }
}
