package com.example.baeminfake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.baeminfake.R;
import com.example.baeminfake.controller.FoodRecyclerViewAdapter;
import com.example.baeminfake.controller.FragmentBottomNavigation;
import com.example.baeminfake.controller.SQLiteCartHelper;
import com.example.baeminfake.controller.SQLiteFoodHelper;
import com.example.baeminfake.model.Cart;
import com.example.baeminfake.model.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentBottomNavigation fragmentBottomNavigation;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private TextView choosemap, cartnoti, getprofile, beefsteak, monchinh, dohop, pizza, trangmieng, fastfood, comsuat, banhmikep, dealhot;
    private EditText search;
    private RecyclerView recyclerView;
    private FoodRecyclerViewAdapter adapter;
    private SQLiteFoodHelper sqLite;
    private SQLiteCartHelper sqLite1;
    private NotificationBadge notificationBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();

//        sqLite = new SQLiteFoodHelper(this);
//        sqLite.addFood(new Food("Trà sữa mlem", 25000, "Trà Sữa Mlem", 4, 100));
//        sqLite.addFood(new Food("Trà sữa ngon", 26000, "Trà Sữa Không Mlem", 3, 100));
//        sqLite.addFood(new Food("Trà sữa hihi", 25500, "Trà Sữa Hihihaha", 5, 100));
//        sqLite.addFood(new Food("Trà sữa nhật bản", 25300, "Trà Sữa Okukaka", 1, 200));
//        sqLite.addFood(new Food("Trà sữa đường", 22000, "Trà Sữa Hohoho", 2, 500));
//        sqLite.addFood(new Food("Trà sữa dở tệ", 21000, "Trà Sữa Mlem", 4, 100));
//        sqLite.addFood(new Food("Trà sữa không ngon", 27000, "Trà Sữa Mlem", 4, 100));
//        sqLite.addFood(new Food("Trà sữa nóng lạnh", 30000, "Trà Sữa Mlem", 2, 100));
//        sqLite.addFood(new Food("Trà sữa âm dương", 15000, "Trà Sữa Hihihaha", 4, 1000));
//        sqLite.addFood(new Food("Trà sữa lên men", 19000, "Trà Sữa Hihihaha", 4, 300));
//        sqLite.addFood(new Food("Trà sữa táo bón", 21000, "Trà Sữa Okukaka", 4, 700));
//        sqLite.addFood(new Food("Trà sữa thái cực", 25000, "Trà Sữa Okukaka", 5, 100));

        adapter = new FoodRecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        sqLite = new SQLiteFoodHelper(this);
        sqLite1 = new SQLiteCartHelper(this);
        List<Food> foods = sqLite.getAll();
        List<Cart> carts = sqLite1.getCartUserPay(currentUser.getUid(), 0);
        adapter.setFoods(foods);
        recyclerView.setAdapter(adapter);

        this.choosemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
                MainActivity.this.finish();
            }
        });

        notificationBadge.setNumber(carts.size());
        this.cartnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                MainActivity.this.finish();
            }
        });

        this.getprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                MainActivity.this.finish();
            }
        });

        this.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                List<Food> list = sqLite.getFoodsByName(str);
                adapter.setFoods(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        fragmentBottomNavigation = new FragmentBottomNavigation(getSupportFragmentManager(), FragmentBottomNavigation.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragmentBottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ads1: {
                        viewPager.setCurrentItem(0);
                        break;
                    }

                    case R.id.ads2: {
                        viewPager.setCurrentItem(1);
                        break;
                    }

                    case R.id.ads3: {
                        viewPager.setCurrentItem(2);
                        break;
                    }
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        choosemap = findViewById(R.id.choose_map);
        cartnoti = findViewById(R.id.cart_noti);
        getprofile = findViewById(R.id.get_profile);
        monchinh = findViewById(R.id.monchinh);
        beefsteak = findViewById(R.id.beefsteak);
        dohop = findViewById(R.id.dohop);
        banhmikep = findViewById(R.id.banhmikep);
        pizza = findViewById(R.id.pizza);
        comsuat = findViewById(R.id.comsuat);
        fastfood = findViewById(R.id.fastfood);
        trangmieng = findViewById(R.id.trangmieng);
        dealhot = findViewById(R.id.dealhot);
        search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.list_item);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        notificationBadge = findViewById(R.id.badge);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        cartnoti.setText(currentUser.getDisplayName());
    }
}