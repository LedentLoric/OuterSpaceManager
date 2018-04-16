package com.example.lledent.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lledent on 16/04/2018.
 */

public class FleetArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final List<Ship> values;

    public FleetArrayAdapter(Context context, List<Ship> values)
    {
        super(context, R.layout.ship_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fleet_row, parent, false);

        TextView shipName = (TextView) rowView.findViewById(R.id.fleetRowNameTextViewID);
        TextView amount = (TextView) rowView.findViewById(R.id.fleetRowAmountTextViewID);

        shipName.setText(values.get(position).name);
        amount.setText(amount.getText() + Integer.toString(values.get(position).amount));

        return rowView;
    }
}
