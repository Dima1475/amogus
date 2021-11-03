package com.example.tele6;

import android.animation.TypeConverter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LotsAdapter extends RecyclerView.Adapter<LotsAdapter.ViewHolder> {

    ArrayList<ItemLot> lotItems;
    public LotsAdapter(ArrayList<ItemLot> lotItems){this.lotItems = lotItems;}
    @NonNull
    @NotNull
    @Override
    public LotsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lots_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LotsAdapter.ViewHolder holder, int position) {
        ItemLot item = lotItems.get(position);
        holder.tvGigabytesCoun.setText(String.valueOf(item.gigabytesToTrade));
        holder.tvPric.setText(String.valueOf(item.price));
    }

    @Override
    public int getItemCount() {
        return lotItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGigabytesCoun;
        TextView tvPric;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvGigabytesCoun = itemView.findViewById(R.id.tvGigabytesCount);
            tvPric = itemView.findViewById(R.id.tvPrice);
        }
    }
}
