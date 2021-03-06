package com.example.baeminfake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.baeminfake.R;
import com.example.baeminfake.controller.FoodRecyclerViewAdapter;
import com.example.baeminfake.controller.FragmentBottomNavigation;
import com.example.baeminfake.controller.RestaurantRecyclerViewAdapter;
import com.example.baeminfake.controller.SQLiteCartHelper;
import com.example.baeminfake.controller.SQLiteFoodHelper;
import com.example.baeminfake.controller.SQLiteRestaurantHelper;
import com.example.baeminfake.model.Cart;
import com.example.baeminfake.model.Food;
import com.example.baeminfake.model.Restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.baeminfake.activity.MapActivity.location;

public class MainActivity extends AppCompatActivity {

    private FragmentBottomNavigation fragmentBottomNavigation;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private TextView choosemap, cartnoti, getprofile, beefsteak, monchinh, dohop, pizza, trangmieng, fastfood, comsuat, banhmikep, text_foru, notifi_main, bigsale, getmore;
    private LinearLayout maybelove, linear_meal, foru, dealhot, drink;
    private RecyclerView list_food, list_restaurant, list_foryou, list_love;
    private FoodRecyclerViewAdapter adapterfood1, adapterfood2, adapterfood3;
    private RestaurantRecyclerViewAdapter adapter1;
    private SQLiteFoodHelper sqLite;
    private SQLiteCartHelper sqLite2;
    private SQLiteRestaurantHelper sqLite1;
    private NotificationBadge notificationBadge;
    private int meal = 0;
    private static List<Integer> list_recommand = new ArrayList<>();
//    public static String locattion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();

//        sqLite = new SQLiteFoodHelper(this);
//        sqLite.addFood(new Food(0, "Tr?? s???a ahihi", 25000, "Tr?? S???a Mlem", 4, 100));
//        sqLite.addFood(new Food(0, "Tr?? s???a cuso", 26000, "Tr?? S???a Mlem", 3, 100));
//        sqLite.addFood(new Food(0, "Cam chanh ????o", 25500, "Tr?? S???a gongcha", 5, 100));
//        sqLite.addFood(new Food(0, "Tr?? s???a nh???t", 25300, "Tr?? S???a gongcha", 1, 200));
//        sqLite.addFood(new Food(1, "S???a chua m??t", 22000, "??n v???t d??ng b??o", 2, 500));
//        sqLite.addFood(new Food(1,"S???a chua s???u ri??ng", 21000, "??n v???t d??ng b??o", 4, 100));
//        sqLite.addFood(new Food(1, "X??c x??ch r??n", 27000, "??n v???t mlem", 4, 100));
//        sqLite.addFood(new Food(2, "B??nh m?? nh??n th???t", 30000, "B??nh m?? savor", 2, 100));
//        sqLite.addFood(new Food(2, "B??nh m?? tr???ng", 15000, "B??nh m?? ao sen", 4, 1000));
//        sqLite.addFood(new Food(2,"B??nh m?? b?? kh??", 19000, "B??nh m?? c?? thu", 4, 300));
//        sqLite.addFood(new Food(2, "B??nh m?? th???p c???m", 21000, "B??nh m?? c?? thu", 4, 700));
//        sqLite.addFood(new Food(2,"B??nh m?? chay", 25000, "B??nh m?? c?? thu", 5, 100));
//        sqLite.addFood(new Food(8,"C??m ni??u", 25000, "C??m ngon ba ghi???n", 5, 500));
//        sqLite.addFood(new Food(8,"C??m b?? kho", 25000, "C??m ngon ba ghi???n", 4, 300));
//        sqLite.addFood(new Food(8,"C??m t???m", 25000, "C??m t???m b??p ph??", 3, 100));
//
//
//        sqLite1 = new SQLiteRestaurantHelper(this);
//        sqLite1.addRestaurant(new Restaurant("Tr?? s???a mlem", "Chuy??n tr?? s???a c??c lo???i", 0));
//        sqLite1.addRestaurant(new Restaurant("Tr?? s???a gongcha", "Chuy??n tr?? s???a ????i loan ????i ph???ng", 0));
//        sqLite1.addRestaurant(new Restaurant("??n v???t d??ng b??o", "S???a chua m??t, s???a chua s???u ri??ng", 0));
//        sqLite1.addRestaurant(new Restaurant("??n v???t mlem", "X??c x??ch, b??nh ??a, b??nh c??y, x??i d???a", 0));
//        sqLite1.addRestaurant(new Restaurant("B??nh m?? savor", "B??nh m?? n??ng gi??n ?????c ru???t kh??ng ngon", 0));
//        sqLite1.addRestaurant(new Restaurant("B??nh m?? ao sen", "B??nh m?? ch???o kh??ng c?? pate", 0));
//        sqLite1.addRestaurant(new Restaurant("B??nh m?? c?? thu", "B??nh m?? ????? lo???i gi?? ch??? 10k", 0));
//        sqLite1.addRestaurant(new Restaurant("BBQ nh?? l??m", "????? l???u c??c lo???i, ??n nh???u t???t ga", 0));
//        sqLite1.addRestaurant(new Restaurant("Ch???ng ph???i BBQ", "????? h??i b???n nh??ng ??n b???n s???ng l??u", 0));
//        sqLite1.addRestaurant(new Restaurant("Pizza Home", "Pizza cun c??t", 0));
//        sqLite1.addRestaurant(new Restaurant("Pizza Hutt", "Pizza c?? ng??? ??n ho??i kh??ng ch??n", 0));
//        sqLite1.addRestaurant(new Restaurant("C??m ngon ba ghi???n", "C??m su???t, c??m ni??u, c??m thi??u, c??m s???ng", 0));
//        sqLite1.addRestaurant(new Restaurant("C??m t???m b??p ph??", "??n kh??ng ngon v???n tr??? ti???n", 0));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String m = sdf.format(cal.getTime());
//        String m = "23:25:00";
//        String m = "03:00:00";
        if (m.compareTo("8:30:00") <= 0 && m.compareTo("4:30:00") > 0) {
            meal = 1;
        } else if ((m.compareTo("08:30:00") > 0 && m.compareTo("10:30:00") <= 0) ||
                (m.compareTo("13:30:00") > 0 && m.compareTo("17:30:00") <= 0) ||
                (m.compareTo("20:00:00") > 0 && m.compareTo("22:30:00") <= 0)) {
            meal = 5;
        } else if (m.compareTo("10:30:00") > 0 && m.compareTo("13:30:00") <= 0) {
            meal = 2;
        } else if (m.compareTo("17:30:00") > 0 && m.compareTo("19:30:00") <= 0) {
            meal = 3;
        } else if ((m.compareTo("22:30:00") > 0 && m.compareTo("23:59:00") <= 0) || (m.compareTo("00:00:00") >= 0 && m.compareTo("01:00:00") <= 0)) {
            meal = 4;
        } else {
            meal = 0;
        }

        adapterfood1 = new FoodRecyclerViewAdapter(this);
        list_food.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_food.setAdapter(adapterfood1);

        adapterfood2 = new FoodRecyclerViewAdapter(this);
        list_foryou.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_foryou.setAdapter(adapterfood2);

        adapterfood3 = new FoodRecyclerViewAdapter(this);
        list_love.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_love.setAdapter(adapterfood3);

        sqLite = new SQLiteFoodHelper(this);

        List<Food> list_drink = sqLite.getFoodsByCategory(0);
        adapterfood1.setFoods(list_drink);
        list_food.setAdapter(adapterfood1);

        List<Food> list_foru = new ArrayList<>();
        switch (meal) {
            case 0: {
                bigsale.setVisibility(View.GONE);
                foru.setVisibility(View.GONE);
                drink.setVisibility(View.GONE);
                dealhot.setVisibility(View.GONE);
                linear_meal.setVisibility(View.GONE);
                maybelove.setVisibility(View.GONE);
                notifi_main.setVisibility(View.VISIBLE);
                cartnoti.setEnabled(false);
                choosemap.setEnabled(false);
                break;
            }
            case 1: {
                text_foru.setText("??n s??ng li???n tay, ????n ngay ng??y m???i!");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(2);
                List<Food> list2 = sqLite.getFoodsByCategory(6);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                break;
            }
            case 2: {
                text_foru.setText("Bu???i tr??a vui v??? c??ng Beamin!");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(3);
                List<Food> list2 = sqLite.getFoodsByCategory(2);
                List<Food> list3 = sqLite.getFoodsByCategory(4);
                List<Food> list4 = sqLite.getFoodsByCategory(8);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                list_foru.addAll(list3);
                list_foru.addAll(list4);
                break;
            }
            case 3: {
                text_foru.setText("Mu???n ??n ngon nh??ng l???i l?????i? ?????t b???a t???i ngay!");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(8);
                List<Food> list2 = sqLite.getFoodsByCategory(5);
                List<Food> list3 = sqLite.getFoodsByCategory(3);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                list_foru.addAll(list3);
                break;
            }
            case 4: {
                text_foru.setText("?????t m??n khuya, c??ng nhau h??a si??u l???n!!!");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(1);
                List<Food> list2 = sqLite.getFoodsByCategory(0);
                List<Food> list3 = sqLite.getFoodsByCategory(4);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                list_foru.addAll(list3);
                break;
            }
            case 5: {
                text_foru.setText("Gi???i lao nh??? nh??ng v???i ????? u???ng v?? ??n v???t nha");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(0);
                List<Food> list2 = sqLite.getFoodsByCategory(1);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                break;
            }
        }

        while (list_foru.size() > 8) {
            list_foru.remove(0);
        }

        adapterfood2.setFoods(list_foru);
        list_foryou.setAdapter(adapterfood2);

        Intent intent = getIntent();
        for (int i = 0; i < list_recommand.size(); i++) {
            if (intent.getIntExtra("recommand", -1) == list_recommand.get(i)) {
                list_recommand.remove(i);
            }
        }
        list_recommand.add(intent.getIntExtra("recommand", -1));

        if (list_recommand.size() > 8) {
            list_recommand.remove(0);
        }

        List<Food> list_love_foru = new ArrayList<>();
        for (int i = list_recommand.size() - 1; i >= 0; i--) {
            if (list_recommand.get(i) != -1) {
                list_love_foru.add(sqLite.getFoodById(list_recommand.get(i)));
            }
        }

        if (list_love_foru != null && list_love_foru.size() != 0) {
            maybelove.setVisibility(View.VISIBLE);
            adapterfood3.setFoods(list_love_foru);
            list_love.setAdapter(adapterfood3);
        } else {
            maybelove.setVisibility(View.GONE);
        }

        adapter1 = new RestaurantRecyclerViewAdapter(this);
        list_restaurant.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_restaurant.setAdapter(adapter1);
        sqLite1 = new SQLiteRestaurantHelper(this);
        List<Restaurant> restaurants = sqLite1.getAll();
        adapter1.setRestaurant(restaurants);
        list_restaurant.setAdapter(adapter1);

        sqLite2 = new SQLiteCartHelper(this);
        List<Cart> carts = sqLite2.getCartUserPay(currentUser.getUid(), 0);

        sqLite.close();
        sqLite1.close();
        sqLite2.close();

        if (location != null && !location.equals("")) {
            String[] str = location.split(",");
            choosemap.setText(str[0]);
        } else {
            choosemap.setText("Get location now!");
        }

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

        this.trangmieng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(1);
            }
        });

        this.fastfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(2);
            }
        });

        this.monchinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(3);
            }
        });

        this.pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(4);
            }
        });

        this.beefsteak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(5);
            }
        });

        this.banhmikep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(6);
            }
        });

        this.dohop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(7);
            }
        });

        this.comsuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(8);
            }
        });

        this.getmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(0);
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
        list_food = findViewById(R.id.list_food);
        list_restaurant = findViewById(R.id.list_restaurant);
        list_foryou = findViewById(R.id.list_foru);
        list_love = findViewById(R.id.list_love);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        notificationBadge = findViewById(R.id.badge);
        maybelove = findViewById(R.id.maybelove);
        text_foru = findViewById(R.id.text_foru);
        bigsale = findViewById(R.id.bigsale);
        notifi_main = findViewById(R.id.notifi_main);
        linear_meal = findViewById(R.id.linear_meal);
        foru = findViewById(R.id.foru);
        dealhot = findViewById(R.id.dealhot);
        drink = findViewById(R.id.drink);
        getmore = findViewById(R.id.getmore_dirnk);

        notifi_main.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        cartnoti.setText(currentUser.getDisplayName());
    }

    private void putValue(int category) {
        Intent intent = new Intent(MainActivity.this, GetMoreActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
        MainActivity.this.finish();
    }
}