package com.example.user.foodtracker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 19/12/2016.
 */

public class CustomAdapter extends BaseAdapter implements Filterable {

    Context mContext;
    LayoutInflater inflater;
    private List<FoodDiary> foodDiaryList = null;
    private ArrayList<FoodDiary> arraylist;

    public CustomAdapter(Context context, List<FoodDiary> foodDiaryList) {
        mContext = context;
        this.foodDiaryList = foodDiaryList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<FoodDiary>();
        this.arraylist.addAll(foodDiaryList);
    }

    public class ViewHolder {
        TextView month;
        TextView date;
        TextView day;
        TextView meal;
        TextView food_eaten;
        TextView kcal;
    }

    @Override
    public int getCount() {
        return foodDiaryList.size();
    }

    @Override
    public FoodDiary getItem(int position) {
        return foodDiaryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
//            Locate the TextViews in activity_main.xml
            holder.month = (TextView) view.findViewById(R.id.month);
            holder.date = (TextView) view.findViewById(R.id.date);
            holder.day = (TextView) view.findViewById(R.id.day);
            holder.meal = (TextView) view.findViewById(R.id.meal);
            holder.food_eaten = (TextView) view.findViewById(R.id.food);
            holder.kcal = (TextView) view.findViewById(R.id.kcal);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.month.setText(foodDiaryList.get(position).getMonth());
        holder.date.setText(foodDiaryList.get(position).getDate().toString());
        holder.day.setText(foodDiaryList.get(position).getDay());
        holder.meal.setText(foodDiaryList.get(position).getMeal());
        holder.food_eaten.setText(foodDiaryList.get(position).getFoodEaten());
        holder.kcal.setText(foodDiaryList.get(position).getKcal());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleItemView.class);
                intent.putExtra("month", (foodDiaryList.get(position).getMonth()));
                intent.putExtra("date", (foodDiaryList.get(position).getDate()));
                intent.putExtra("day", (foodDiaryList.get(position).getDay()));
                intent.putExtra("meal", (foodDiaryList.get(position).getMeal()));
                intent.putExtra("food_eaten", (foodDiaryList.get(position).getFoodEaten()));
                intent.putExtra("kcal", (foodDiaryList.get(position).getKcal()));
                mContext.startActivity(intent);
            }
        });
        return view;

    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                foodDiaryList = (List<FoodDiary>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<String> FilteredArrayNames = new ArrayList<String>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                if (constraint.length() == 0) {
                    foodDiaryList.addAll(arraylist);
                } else {
                    for (FoodDiary fd : arraylist) {
                        if (fd.getMonth().toLowerCase(Locale.getDefault()).contains(constraint)) {
                            FilteredArrayNames.add(fd.toString());
                        }
                    }
                }


                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }
}


