package com.example.baeminfake.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baeminfake.R;
import com.example.baeminfake.controller.CartRecyclerViewAdapter;
import com.example.baeminfake.controller.SQLiteCartHelper;
import com.example.baeminfake.model.Cart;

import java.util.List;

public class CartFragment extends Fragment {

    private TextView turnback, pay;
    private RecyclerView recyclerView;
    private CartRecyclerViewAdapter adapter;
    private SQLiteCartHelper sqLite;

    public CartFragment() {
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
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
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        init(view);

        adapter = new CartRecyclerViewAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        sqLite = new SQLiteCartHelper(getActivity());
        List<Cart> carts = sqLite.getCartPay(0);
        adapter.setCarts(carts);
        recyclerView.setAdapter(adapter);

        this.turnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        this.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.idcart != 0 && adapter.checkpaycart != 0) {
                    Cart c = sqLite.getCartById(adapter.idcart);
                    c.setPayment(1);
                    sqLite.update(c);
                    Fragment current = new MainFragment();
                    getFragment(current);
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void init(View view) {
        turnback = view.findViewById(R.id.back_to_main3);
        pay = view.findViewById(R.id.pay);
        recyclerView = view.findViewById(R.id.list_cart);
    }

    public void getFragment(Fragment current) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_holder, current)
                .addToBackStack(null)
                .commit();
    }
}
