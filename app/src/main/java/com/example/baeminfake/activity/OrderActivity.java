package com.example.baeminfake.activity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.baeminfake.R;
import com.example.baeminfake.controller.NotificationApp;
import com.example.baeminfake.controller.SQLiteCartHelper;
import com.example.baeminfake.controller.SQLiteFoodHelper;
import com.example.baeminfake.model.Cart;
import com.example.baeminfake.model.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OrderActivity extends AppCompatActivity {

    private TextView turnBack, address, name, price, restaurant, order, giam, tang, cancel, voucher, shipping, total, addCart;
    private ImageView order_img;
    private EditText district, soLuong, note;
    private RatingBar rate;
    private SQLiteFoodHelper sqLite;
    private SQLiteCartHelper sqLite1;
    private NotificationManagerCompat notificationManagerCompat;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.order_activity);
        init();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

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
        this.notificationManagerCompat = NotificationManagerCompat.from(this);

        this.turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this, MainActivity.class));
                OrderActivity.this.finish();
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this, MainActivity.class));
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
                Cart c = new Cart(firebaseUser.getUid(), 0, Integer.parseInt(soLuong.getText().toString()), k.getName(), Double.parseDouble(total.getText().toString()), k.getRestaurant(), k.getRate(), k.getOrders());
                sqLite1.addCart(c);
                Notification notification = new NotificationCompat.Builder(OrderActivity.this, NotificationApp.CHANNEL)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Cập nhật giỏ hàng:")
                        .setContentText("Bạn vừa thêm mới một mặt hàng vào giỏi! Kiểm tra ngay")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                OrderActivity.this.notificationManagerCompat.notify(1, notification);
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
        cancel = findViewById(R.id.cancel_order);
        district = findViewById(R.id.add_district);
        note = findViewById(R.id.note_for_restaurant);
        voucher = findViewById(R.id.select_voucher);
        shipping = findViewById(R.id.select_shipping);
        addCart = findViewById(R.id.add_to_cart);
        total = findViewById(R.id.total_price);
    }
}
