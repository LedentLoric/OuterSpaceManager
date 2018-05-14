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
//        super(context, R.layout.ship_row, values);
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
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
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

//    // convenience method for getting data at click position
//    Ship getItem(int id) {
//        return values.get(id);
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }

    // method with ListView
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View rowView = inflater.inflate(R.layout.fleet_row, parent, false);
//
//        TextView shipName = (TextView) rowView.findViewById(R.id.fleetRowNameTextViewID);
//        TextView amount = (TextView) rowView.findViewById(R.id.fleetRowAmountTextViewID);
//
//        shipName.setText(values.get(position).name);
//        amount.setText(amount.getText() + Integer.toString(values.get(position).amount));
//
//        return rowView;
//    }
}
