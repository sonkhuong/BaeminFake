package com.example.baeminfake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baeminfake.R;
import com.example.baeminfake.controller.SQLiteCartHelper;
import com.example.baeminfake.controller.SQLiteFoodHelper;
import com.example.baeminfake.model.Cart;
import com.example.baeminfake.model.Food;

public class OrderActivity extends AppCompatActivity {

    private TextView turnBack, address, name, price, restaurant, order, giam, tang, cancel, voucher, shipping, total, addCart;
    private ImageView order_img;
    private EditText district, soLuong, note;
    private RatingBar rate;
    private SQLiteFoodHelper sqLite;
    private SQLiteCartHelper sqLite1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.order_activity);
        init();

        sqLite = new SQLiteFoodHelper(this);
        sqLite1 = new SQLiteCartHelper(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("food", 0);
        Food k = sqLite.getFoodById(id);
        name.setText(k.getName());
        price.setText(k.getPrice() + "$");
        restaurant.setText(k.getRestaurant());
        rate.setRating(k.getRate());
        order.setText("(" + k.getOrders() + "+)");
        soLuong.setText("1");
        total.setText(k.getPrice() + "");

        this.turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderActivity.this.onBackPressed();
                OrderActivity.this.finish();
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderActivity.this.onBackPressed();
                OrderActivity.this.finish();
            }
        });

        this.tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong.setText((Integer.parseInt(soLuong.getText().toString()) + 1) + "");
                total.setText(k.getPrice() * Integer.parseInt(soLuong.getText().toString()) + "");
            }
        });

        this.giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong.setText((Integer.parseInt(soLuong.getText().toString()) - 1) + "");
                total.setText(k.getPrice() * Integer.parseInt(soLuong.getText().toString()) + "");
            }
        });

        this.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart c = new Cart(0, Integer.parseInt(soLuong.getText().toString()), k.getName(), Double.parseDouble(total.getText().toString()), k.getRestaurant(), k.getRate(), k.getOrders());
                sqLite1.addCart(c);
                startActivity(new Intent(OrderActivity.this, MainActivity.class));
                OrderActivity.this.finish();
            }
        });
    }

    public void init() {
        turnBack = findViewById(R.id.back_to_main1);
        address = findViewById(R.id.address);
        name = findViewById(R.id.order_name);
        order_img = findViewById(R.id.order_img);
        price = findViewById(R.id.order_price);
        restaurant = findViewById(R.id.order_restaurant);
        order = findViewById(R.id.order_order);
        rate = findViewById(R.id.order_rate);
        giam = findViewById(R.id.giam_sl);
        tang = findViewById(R.id.tang_sl);
        soLuong = findViewById(R.id.soluong);
        cancel = findViewById(R.id.delete_order);
        district = findViewById(R.id.add_district);
        note = findViewById(R.id.note_for_restaurant);
        voucher = findViewById(R.id.select_voucher);
        shipping = findViewById(R.id.select_shipping);
        addCart = findViewById(R.id.add_to_cart);
        total = findViewById(R.id.total_price);
    }
}
