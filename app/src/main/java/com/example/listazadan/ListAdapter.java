package com.example.listazadan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {

    ArrayList<String> items;

    public ListAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        ItemHolder holder = new ItemHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        String value = items.get(position);
        holder.tvValue.setText(value);

        holder.bRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getAdapterPosition();

                items.remove(itemPosition);
                notifyItemRemoved(itemPosition);
            }
        });

        holder.cbChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int itemPosition = holder.getAdapterPosition();
                String oldValue = items.get(itemPosition);

                if(isChecked) {
                    items.remove(itemPosition);
                    items.add(oldValue);

                    notifyItemMoved(itemPosition, items.size() - 1);
                } else {
                    items.remove(itemPosition);
                    items.add(0, oldValue);

                    notifyItemMoved(itemPosition, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvValue;
        Button bRemove;
        CheckBox cbChecked;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            tvValue = itemView.findViewById(R.id.tvValue);
            bRemove = itemView.findViewById(R.id.bRemove);
            cbChecked = itemView.findViewById(R.id.cbChecked);
        }
    }
}
