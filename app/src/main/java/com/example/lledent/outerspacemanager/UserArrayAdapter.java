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

public class UserArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final List<UserScore> values;

    public UserArrayAdapter(Context context, List<UserScore> values)
    {
        super(context, R.layout.ship_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.galaxy_row, parent, false);

        TextView rank = (TextView) rowView.findViewById(R.id.galaxyRowRankTextViewID);
        TextView username = (TextView) rowView.findViewById(R.id.galaxyRowUsernameTextViewID);
        TextView points = (TextView) rowView.findViewById(R.id.galaxyRowPointsTextViewID);

        rank.setText(Integer.toString(position + 1));
        username.setText(values.get(position).getUsername());
        points.setText(Long.toString(values.get(position).getPoints()));

        return rowView;
    }
}
