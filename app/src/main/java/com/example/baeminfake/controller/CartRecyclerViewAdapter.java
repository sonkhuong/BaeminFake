package com.example.baeminfake.controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baeminfake.R;
import com.example.baeminfake.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.CartsViewHolder> {

    private List<Cart> list;
    private Activity activity;
    public int idcart = 0;
    public int checkpaycart = 0;

    public CartRecyclerViewAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
    }

    public void setCarts(List<Cart> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartsViewHolder holder, int position) {
        Cart o = list.get(position);
        if (o.getPayment() == 1) {
            holder.checkpay.setChecked(true);
        } else {
            holder.checkpay.setChecked(false);
        }
        holder.txtsoluong.setText(o.getSoluong() + "");
        holder.txtName.setText(o.getName());
        holder.txtPrice.setText("" + o.getPrice());
        holder.txtRestaurant.setText(o.getRestaurant());
        holder.rating.setRating(o.getRate());
        holder.txtOrder.setText("(" + o.getOrders() + "+)");
        holder.imgItem.setImageResource(R.drawable.trasua);

        Activity activity = this.activity;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idcart = o.getId();
                if (holder.checkpay.isChecked() == true) {
                    holder.checkpay.setChecked(false);
                    checkpaycart = 0;
                } else {
                    holder.checkpay.setChecked(true);
                    checkpaycart = 1;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    class CartsViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtOrder, txtPrice, txtRestaurant, txtsoluong, txtDate;
        private ImageView imgItem;
        private RatingBar rating;
        private CheckBox checkpay;

        public CartsViewHolder(@NonNull View v) {
            super(v);
            txtsoluong = v.findViewById(R.id.soluong_pay);
            checkpay = v.findViewById(R.id.check_pay);
            txtName = v.findViewById(R.id.cart_name);
            txtPrice = v.findViewById(R.id.cart_price);
            txtRestaurant = v.findViewById(R.id.cart_restaurant);
            imgItem = v.findViewById(R.id.cart_img);
            rating = v.findViewById(R.id.cart_rate);
            txtOrder = v.findViewById(R.id.cart_order);
            txtDate = v.findViewById(R.id.cart_date);
        }
    }
}
