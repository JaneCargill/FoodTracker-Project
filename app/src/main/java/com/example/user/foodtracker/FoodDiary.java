package com.example.user.foodtracker;

/**
 * Created by user on 16/12/2016.
 */

public class FoodDiary {

    private int id;
    private String name;
    private String phone_number;

    public FoodDiary(int id, String name, String phone_number) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
    }
    public FoodDiary(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
