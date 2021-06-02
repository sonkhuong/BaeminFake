package com.example.baeminfake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baeminfake.R;
import com.example.baeminfake.controller.CartRecyclerViewAdapter;
import com.example.baeminfake.controller.SQLiteCartHelper;
import com.example.baeminfake.model.Cart;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private TextView turnback, pay;
    private RecyclerView recyclerView;
    private CartRecyclerViewAdapter adapter;
    private SQLiteCartHelper sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.cart_activity);

        turnback = findViewById(R.id.back_to_main3);
        pay = findViewById(R.id.pay);
        recyclerView = findViewById(R.id.list_cart);

        adapter = new CartRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLite = new SQLiteCartHelper(this);
        List<Cart> carts = sqLite.getCartPay(0);
        adapter.setCarts(carts);
        recyclerView.setAdapter(adapter);
//        recyclerView.setEnabled(true);

        this.turnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
                CartActivity.this.finish();
            }
        });

        this.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 100; i++) {
                    if (adapter.chooseid[i] == 1) {
                        Cart c = sqLite.getCartById(i);
                        c.setPayment(1);
                        sqLite.update(c);
                        startActivity(new Intent(CartActivity.this, MainActivity.class));
                        CartActivity.this.finish();
                    }
                }
            }
        });
    }
}