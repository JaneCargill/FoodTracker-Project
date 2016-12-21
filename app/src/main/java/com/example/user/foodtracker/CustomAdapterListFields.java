package com.example.user.foodtracker;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 21/12/2016.
 */

public class CustomAdapterListFields extends ArrayAdapter<FoodDiary> {

//        public CustomAdapterListFields(Context context, int textViewResourceId) {
//            super(context, textViewResourceId);
//        }

        public CustomAdapterListFields (Context context, List<FoodDiary> foodDiaryList) {
            super(context, 0, foodDiaryList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            FoodDiary foodEntry = getItem(position);

            if (convertView == null) {
                LayoutInflater li;
                li = LayoutInflater.from(getContext());
                convertView = li.inflate(R.layout.itemlistrow, null);
            }


            if (foodEntry != null) {
                TextView tt1 = (TextView) convertView.findViewById(R.id.month);
                TextView tt2 = (TextView) convertView.findViewById(R.id.date);
                TextView tt3 = (TextView) convertView.findViewById(R.id.day);
                TextView tt4 = (TextView) convertView.findViewById(R.id.meal);
                TextView tt5 = (TextView) convertView.findViewById(R.id.food);
                TextView tt6 = (TextView) convertView.findViewById(R.id.kcal);

                if (tt1 != null) {
                    tt1.setText(Integer.toString(foodEntry.getID()));
                }

                if (tt2 != null) {
                    tt2.setText(foodEntry.getDate());
                }

                if (tt3 != null) {
                    tt3.setText(foodEntry.getDay());
                }

                if (tt4 != null) {
                    tt4.setText(foodEntry.getMeal());
                }

                if (tt5 != null) {
                    tt5.setText(foodEntry.getFoodEaten());
                }

                if (tt6 != null) {
                    tt6.setText(Integer.toString(foodEntry.getKcal()));
                }
            }

            return convertView;
        }

    }
