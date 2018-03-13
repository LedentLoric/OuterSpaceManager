package com.example.lledent.outerspacemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by lledent on 13/03/2018.
 */

public class OverviewActivity extends AppCompatActivity {

    private String jsonUser;
    private CurrentUserResponse user;

    private TextView username;
    private TextView points;
    private TextView gas;
    private TextView gasModifier;
    private TextView minerals;
    private TextView mineralsModifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_activity);

        username = (TextView) findViewById(R.id.overviewNameTextViewID);
        points = (TextView) findViewById(R.id.overviewPointTextViewID);
        gas = (TextView) findViewById(R.id.overviewGasTextViewID);
        gasModifier = (TextView) findViewById(R.id.overviewGasModifierTextViewID);
        minerals = (TextView) findViewById(R.id.overviewMineralsTextViewID);
        mineralsModifier = (TextView) findViewById(R.id.overviewMineralsModifierTextViewID);

        jsonUser = getIntent().getStringExtra("currentUser");

        Gson gson = new Gson();
        user = gson.fromJson(jsonUser, CurrentUserResponse.class);

        username.setText(user.username);
        points.setText(points.getText() + Integer.toString(user.points));
        gas.setText(gas.getText() + Float.toString(user.gas));
        gasModifier.setText(gasModifier.getText() + Float.toString(user.gasModifier));
        minerals.setText(minerals.getText() + Float.toString(user.minerals));
        mineralsModifier.setText(mineralsModifier.getText() + Float.toString(user.mineralsModifier));
    }
}
