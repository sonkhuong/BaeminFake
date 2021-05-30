package com.example.baeminfake.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.baeminfake.model.Cart;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SQLiteCartHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cart1.db";
    private static final int DATABSE_VERSION = 1;

    public SQLiteCartHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        String sql = "create table cart(" +
                "id integer primary key autoincrement," +
                "payment integer," +
                "soluong integer," +
                "name text," +
                "price double," +
                "restaurant text," +
                "rate integer," +
                "orders integer" +
                ")";
        db.execSQL(sql);
    }

    public long addCart(Cart o) {
        ContentValues c = new ContentValues();
        c.put("payment", o.getPayment());
        c.put("soluong", o.getSoluong());
        c.put("name", o.getName());
        c.put("price", o.getPrice());
        c.put("restaurant", o.getRestaurant());
        c.put("rate", o.getRate());
        c.put("orders", o.getOrders());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("cart", null, c);
    }

    public List<Cart> getAll() {
        List<Cart> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor rs = statement.query("cart", null,
                null, null,
                null, null, null);
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            int payment = rs.getInt(1);
            int soluong = rs.getInt(2);
            String name = rs.getString(3);
            double price = rs.getDouble(4);
            String restaurant = rs.getString(5);
            int rating = rs.getInt(6);
            int orders = rs.getInt(7);
            list.add(new Cart(id, payment, soluong, name, price, restaurant, rating, orders));
        }
        return list;
    }

    public Cart getCartById(int id) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("cart", null, whereClause,
                whereArgs, null, null, null);
        if (rs.moveToNext()) {
            int payment = rs.getInt(1);
            int soluong = rs.getInt(2);
            String name = rs.getString(3);
            double price = rs.getDouble(4);
            String restaurant = rs.getString(5);
            int rating = rs.getInt(6);
            int orders = rs.getInt(7);
            Cart o = new Cart(id, payment, soluong, name, price, restaurant, rating, orders);
            return o;
        }
        return null;
    }

    public List<Cart> getCartByName(String name) {
        String whereClause = "name like ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("cart", null, whereClause, whereArgs, null, null, null);
        List<Cart> carts = new ArrayList<>();
        while (rs.moveToNext()) {
            int id = rs.getInt(0);
            int payment = rs.getInt(1);
            int soluong = rs.getInt(2);
            String oname = rs.getString(3);
            double price = rs.getDouble(4);
            String restaurant = rs.getString(5);
            int rating = rs.getInt(6);
            int orders = rs.getInt(7);
            Cart o = new Cart(id, payment, soluong, oname, price, restaurant, rating, orders);
            carts.add(o);
        }
        return carts;
    }

    public List<Cart> getCartPay(int checkpay) {
        String whereClause = "payment like ?";
        String[] whereArgs = {"%" + checkpay + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("cart", null, whereClause, whereArgs, null, null, null);
        List<Cart> carts = new ArrayList<>();
        while (rs.moveToNext()) {
            int id = rs.getInt(0);
            int payment = rs.getInt(1);
            int soluong = rs.getInt(2);
            String oname = rs.getString(3);
            double price = rs.getDouble(4);
            String restaurant = rs.getString(5);
            int rating = rs.getInt(6);
            int orders = rs.getInt(7);
            Cart o = new Cart(id, payment, soluong, oname, price, restaurant, rating, orders);
            carts.add(o);
        }
        return carts;
    }

    public int update(Cart o) {
        ContentValues c = new ContentValues();
        c.put("payment", o.getPayment());
        c.put("soluong", o.getSoluong());
        c.put("name", o.getName());
        c.put("price", o.getPrice());
        c.put("restaurant", o.getRestaurant());
        c.put("rate", o.getRate());
        c.put("orders", o.getOrders());
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(o.getId())};
        return st.update("cart", c, whereClause, whereArgs);
    }

    public int delete(int id) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("cart", whereClause, whereArgs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
