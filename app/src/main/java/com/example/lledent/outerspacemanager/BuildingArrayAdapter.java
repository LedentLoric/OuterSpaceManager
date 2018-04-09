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
 * Created by lledent on 23/01/2018.
 */

public class BuildingArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final List<Building> values;

    public BuildingArrayAdapter(Context context, List<Building> values) {
        super(context, R.layout.building_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.building_row, parent, false);

        ImageView buildingImage = (ImageView) rowView.findViewById(R.id.buildingRowImageID);
        TextView buildingName = (TextView) rowView.findViewById(R.id.buildingRowNameTextViewID);

        Glide.with(rowView).load(values.get(position).imageUrl).into(buildingImage);
        buildingName.setText(values.get(position).name);

        return rowView;
    }
}
