package com.example.lledent.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lledent on 09/04/2018.
 */

public class ShipArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final List<Ship> values;

    public ShipArrayAdapter(Context context, List<Ship> values)
    {
        super(context, R.layout.ship_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ship_row, parent, false);

        TextView shipName = (TextView) rowView.findViewById(R.id.shipRowNameTextViewID);
        TextView shipLife = (TextView) rowView.findViewById(R.id.shipRowSpatioTextViewID);

        shipName.setText(values.get(position).name);
        shipLife.setText("Spatioport Level : " + Integer.toString(values.get(position).spatioportLevelNeeded));

        return rowView;
    }
}
