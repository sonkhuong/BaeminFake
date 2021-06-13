package com.example.baeminfake.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baeminfake.R;
import com.example.baeminfake.activity.OrderActivity;
import com.example.baeminfake.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodsViewHolder> {

    private List<Food> list;
    private Activity activity;

    public FoodRecyclerViewAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
    }

    public void setFoods(List<Food> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.food_item, parent, false);
        return new FoodsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
        Food o = list.get(position);
        holder.txtName.setText(o.getName());
        holder.txtPrice.setText("" + o.getPrice());
        holder.txtRestaurant.setText(o.getRestaurant());
        holder.rating.setRating(o.getRate());
        holder.txtOrder.setText("(" + o.getOrders() + "+)");
        switch (o.getCategory()) {
            case 0: {
                holder.imgItem.setImageResource(R.drawable.trasua);
                break;
            }
            case 1: {
                holder.imgItem.setImageResource(R.drawable.anvat);
                break;
            }
            case 2: {
                holder.imgItem.setImageResource(R.drawable.banhmi);
                break;
            }
            case 3: {
                holder.imgItem.setImageResource(R.drawable.monchinh);
                break;
            }
            case 4: {
                holder.imgItem.setImageResource(R.drawable.pizza);
                break;
            }
            case 5: {
                holder.imgItem.setImageResource(R.drawable.beefsteak);
                break;
            }
            case 6: {
                holder.imgItem.setImageResource(R.drawable.sandwich);
                break;
            }
            case 7: {
                holder.imgItem.setImageResource(R.drawable.dohop);
                break;
            }
            case 8: {
                holder.imgItem.setImageResource(R.drawable.comsuat);
                break;
            }
        }

        Activity activity = this.activity;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, OrderActivity.class);
                intent.putExtra("food", o.getId());
                activity.startActivity(intent);
                activity.finish();
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

    class FoodsViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtOrder, txtPrice, txtRestaurant;
        private ImageView imgItem;
        private RatingBar rating;

        public FoodsViewHolder(@NonNull View v) {
            super(v);
            txtName = v.findViewById(R.id.item_name);
            txtPrice = v.findViewById(R.id.item_price);
            txtRestaurant = v.findViewById(R.id.item_restaurant);
            imgItem = v.findViewById(R.id.item_img);
            rating = v.findViewById(R.id.item_rate);
            txtOrder = v.findViewById(R.id.item_order);
        }
    }
}
