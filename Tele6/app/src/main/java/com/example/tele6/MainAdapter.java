package com.example.tele6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<Item> items;

    public MainAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.teTitle.setText(item.title);
        holder.teDescription.setText(item.description);
        holder.btnStatus.setText(item.idStatus.toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView teTitle;
        TextView teDescription;
        Button btnStatus;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            teTitle = itemView.findViewById(R.id.tvItemTitle);
            teDescription = itemView.findViewById(R.id.tvItemDescription);
            btnStatus = itemView.findViewById(R.id.btnStatus);
        }
    }
}
