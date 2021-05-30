package com.example.baeminfake.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.baeminfake.R;

import static com.example.baeminfake.activity.MainActivity.user;

public class ProfileFragment extends Fragment {

    private TextView name, turnback, myorder, favorite, history, wallet, address, support;
    private ImageButton verifi;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        init(view);

        this.turnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        this.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new InfoFragment();
                getFrament(current);
            }
        });

        verifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new InfoFragment();
                getFrament(current);
            }
        });

        this.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new HistoryFragment();
                getFrament(current);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void init(View view) {
        turnback = view.findViewById(R.id.back_to_main2);
        name = view.findViewById(R.id.name_profile);
        verifi = view.findViewById(R.id.img_verifi);
        myorder = view.findViewById(R.id.my_order);
        favorite = view.findViewById(R.id.favorite);
        history = view.findViewById(R.id.purchase_history);
        wallet = view.findViewById(R.id.wallet_coupon);
        address = view.findViewById(R.id.address_manager);
        support = view.findViewById(R.id.support);
        name.setText(user.getName());
    }

    public void getFrament(Fragment current) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_holder, current)
                .addToBackStack(null)
                .commit();
    }
}
