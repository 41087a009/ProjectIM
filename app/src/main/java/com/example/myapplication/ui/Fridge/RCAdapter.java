package com.example.myapplication.ui.Fridge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.FoodsViewHolder> {

    private List<Foods> foodsList;

    public RCAdapter(List<Foods> data) {
        this.foodsList = data;
    }

    // 建立 ViewHolder
    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_foods, parent, false);
        return new FoodsViewHolder(view);
    }

    // 綁定數據
    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
        holder.txv.setText(foodsList.get(position).getName());
        holder.txv2.setText(foodsList.get(position).getDate());
        holder.txv3.setText(foodsList.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return foodsList == null ? 0 : foodsList.size();
    }



    public class FoodsViewHolder extends RecyclerView.ViewHolder {
        private TextView txv,txv2,txv3;

        public FoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            txv = itemView.findViewById(R.id.txv_FoodsName);
            txv2 = itemView.findViewById(R.id.txv_FoodsDate);
            txv3= itemView.findViewById(R.id.txv_FoodsAmount);

        }
    }
}
