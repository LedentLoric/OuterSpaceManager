package com.example.lledent.outerspacemanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lledent on 16/04/2018.
 */

public class FleetArrayAdapter extends RecyclerView.Adapter<FleetArrayAdapter.ViewHolder> {
    private final Context context;
    private final List<Ship> values;
    private LayoutInflater mInflater;
//    private ItemClickListener mClickListener;

    public FleetArrayAdapter(Context context, List<Ship> values)
    {
        this.context = context;
        this.values = values;
        this.mInflater = LayoutInflater.from(context);
    }

    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView shipName;
        TextView amount;

        public ViewHolder(final View itemView) {
            super(itemView);
            shipName = itemView.findViewById(R.id.fleetRowNameTextViewID);
            amount = itemView.findViewById(R.id.fleetRowAmountTextViewID);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = mInflater.inflate(R.layout.fleet_row, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.shipName.setText(values.get(position).name);
        holder.amount.setText(holder.amount.getText() + Integer.toString(values.get(position).amount));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
