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
        TextView shipLife = (TextView) rowView.findViewById(R.id.shipRowLifeTextViewID);
        TextView shipAttack = (TextView) rowView.findViewById(R.id.shipRowAttTextViewID);
        TextView shipShield = (TextView) rowView.findViewById(R.id.shipRowShieldTextViewID);

        shipName.setText(values.get(position).name);
        shipLife.setText("Life : " + Integer.toString(values.get(position).life));
        shipAttack.setText("Attack : " + Integer.toString(values.get(position).maxAttack));
        shipShield.setText("Shield : " + Integer.toString(values.get(position).shield));

        return rowView;
    }
}
