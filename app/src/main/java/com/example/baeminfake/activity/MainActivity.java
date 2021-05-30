package com.example.baeminfake.activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.baeminfake.R;
import com.example.baeminfake.fragment.MainFragment;
import com.example.baeminfake.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    public static User user;
//    private SQLiteFoodHelper sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//        sqLite = new SQLiteFoodHelper(this);
//        sqLite.addFood(new Food("Trà sữa ngon mlem mlem", 25000, "Trà Sữa Mlem", 4, 100));
//        sqLite.addFood(new Food("Trà sữa ngon mlem mlem", 26000, "Trà Sữa Không Mlem", 3, 100));
//        sqLite.addFood(new Food("Trà sữa ngon mlem mlem", 25500, "Trà Sữa Hihihaha", 5, 100));
//        sqLite.addFood(new Food("Trà sữa ngon mlem mlem", 25300, "Trà Sữa Okukaka", 1, 200));
//        sqLite.addFood(new Food("Trà sữa ngon mlem mlem", 22000, "Trà Sữa Hohoho", 2, 500));
//        sqLite.addFood(new Food("Trà sữa dở tệ", 21000, "Trà Sữa Mlem", 4, 100));
//        sqLite.addFood(new Food("Trà sữa không ngon", 27000, "Trà Sữa Mlem", 4, 100));
//        sqLite.addFood(new Food("Trà sữa nóng lạnh", 30000, "Trà Sữa Mlem", 2, 100));
//        sqLite.addFood(new Food("Trà sữa âm dương", 15000, "Trà Sữa Hihihaha", 4, 1000));
//        sqLite.addFood(new Food("Trà sữa lên men", 19000, "Trà Sữa Hihihaha", 4, 300));
//        sqLite.addFood(new Food("Trà sữa táo bón", 21000, "Trà Sữa Okukaka", 4, 700));
//        sqLite.addFood(new Food("Trà sữa thái cực", 25000, "Trà Sữa Okukaka", 5, 100));

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        user = new User(currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber());

        Fragment current = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_holder, current, "TAG")
                .addToBackStack(null)
                .commit();
    }
}