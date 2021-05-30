package com.example.baeminfake.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baeminfake.R;
import com.example.baeminfake.controller.FoodRecyclerViewAdapter;
import com.example.baeminfake.controller.SQLiteFoodHelper;
import com.example.baeminfake.model.Food;

import java.util.List;

import static com.example.baeminfake.activity.MainActivity.user;

public class MainFragment extends Fragment {

    private TextView choosemap, cartnoti, getprofile, beefsteak, monchinh, dohop, pizza, trangmieng, fastfood, comsuat, banhmikep, dealhot;
    private EditText search;
    private RecyclerView recyclerView;
    private FoodRecyclerViewAdapter adapter;
    private SQLiteFoodHelper sqLite;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        init(view);
        adapter = new FoodRecyclerViewAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        sqLite = new SQLiteFoodHelper(getActivity());
        List<Food> foods = sqLite.getAll();
        adapter.setFoods(foods);
        recyclerView.setAdapter(adapter);

        this.cartnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new CartFragment();
                getFragment(current);
            }
        });

        this.getprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new ProfileFragment();
                getFragment(current);
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void init(View view) {
        choosemap = view.findViewById(R.id.choose_map);
        cartnoti = view.findViewById(R.id.cart_noti);
        getprofile = view.findViewById(R.id.get_profile);
        monchinh = view.findViewById(R.id.monchinh);
        beefsteak = view.findViewById(R.id.beefsteak);
        dohop = view.findViewById(R.id.dohop);
        banhmikep = view.findViewById(R.id.banhmikep);
        pizza = view.findViewById(R.id.pizza);
        comsuat = view.findViewById(R.id.comsuat);
        fastfood = view.findViewById(R.id.fastfood);
        trangmieng = view.findViewById(R.id.trangmieng);
        dealhot = view.findViewById(R.id.dealhot);
        search = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.list_item);
        cartnoti.setText(user.getName());
    }

    public void getFragment(Fragment current) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_holder, current)
                .addToBackStack(null)
                .commit();
    }
}
