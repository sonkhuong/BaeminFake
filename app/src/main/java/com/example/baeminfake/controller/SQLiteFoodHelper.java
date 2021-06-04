package com.example.baeminfake.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.baeminfake.model.Food;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SQLiteFoodHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "food.db";
    private static final int DATABSE_VERSION = 1;

    public SQLiteFoodHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        String sql = "create table food(" +
                "id integer primary key autoincrement," +
                "name text," +
                "price double," +
                "restaurant text," +
                "rate integer," +
                "orders integer" +
                ")";
        db.execSQL(sql);
    }

    public long addFood(Food o) {
        ContentValues c = new ContentValues();
        c.put("name", o.getName());
        c.put("price", o.getPrice());
        c.put("restaurant", o.getRestaurant());
        c.put("rate", o.getRate());
        c.put("orders", o.getOrders());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("food", null, c);
    }

    public List<Food> getAll() {
        List<Food> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor rs = statement.query("food", null,
                null, null,
                null, null, null);
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            double price = rs.getDouble(2);
            String restaurant = rs.getString(3);
            int rating = rs.getInt(4);
            int orders = rs.getInt(5);
            list.add(new Food(id, name, price, restaurant, rating, orders));
        }
        return list;
    }

    public Food getFoodById(int id) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("food", null, whereClause,
                whereArgs, null, null, null);
        if (rs.moveToNext()) {
            String name = rs.getString(1);
            double price = rs.getDouble(2);
            String restaurant = rs.getString(3);
            int rating = rs.getInt(4);
            int orders = rs.getInt(5);
            Food o = new Food(id, name, price, restaurant, rating, orders);
            return o;
        }
        return null;
    }

    public List<Food> getFoodsByName(String name) {
        String whereClause = "name like ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("food", null, whereClause, whereArgs, null, null, null);
        List<Food> foods = new ArrayList<>();
        while (rs.moveToNext()) {
            int id = rs.getInt(0);
            String oname = rs.getString(1);
            double price = rs.getDouble(2);
            String restaurant = rs.getString(3);
            int rating = rs.getInt(4);
            int orders = rs.getInt(5);
            Food o = new Food(id, oname, price, restaurant, rating, orders);
            foods.add(o);
        }
        return foods;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
