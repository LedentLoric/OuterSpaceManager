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

public class SearchesArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final List<Search> values;

    public SearchesArrayAdapter(Context context, List<Search> values)
    {
        super(context, R.layout.ship_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.searches_row, parent, false);

        TextView searchName = (TextView) rowView.findViewById(R.id.searchesRowNameTextViewID);
        TextView searchEffect = (TextView) rowView.findViewById(R.id.searchesRowEffectTextViewID);

        searchName.setText(values.get(position).getName());
        searchEffect.setText(searchEffect.getText() + values.get(position).getEffect());

        return rowView;
    }
}
