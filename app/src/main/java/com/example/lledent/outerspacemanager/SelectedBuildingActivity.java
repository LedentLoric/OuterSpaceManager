package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

/**
 * Created by lledent on 27/02/2018.
 */

public class SelectedBuildingActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private String token;

    private ImageView image;
    private TextView name;
    private TextView level;
    private TextView effect;
    private TextView gasCost;
    private TextView gasCostByLv;
    private TextView mineralCost;
    private TextView mineralCostByLv;
    private TextView amounOftEffect;
    private TextView amounOftEffectByLv;
    private TextView timeToBuild;
    private TextView timeToBuildByLv;

    private String jsonBuilding;
    private Building building;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_building_activity);
        image = (ImageView) findViewById(R.id.selectedBuildingImageID);
        name = (TextView) findViewById(R.id.selectedBuildingNameTextViewID);
        level = (TextView) findViewById(R.id.selectedBuildingLevelTextViewID);
        effect = (TextView) findViewById(R.id.selectedBuildingEffectTextViewID);
        gasCost = (TextView) findViewById(R.id.selectedBuildingGasCostTextViewID);
        gasCostByLv = (TextView) findViewById(R.id.selectedBuildingGasCostByLevelTextViewID);
        mineralCost = (TextView) findViewById(R.id.selectedBuildingMineralCostTextViewID);
        mineralCostByLv = (TextView) findViewById(R.id.selectedBuildingMineralCostByLevelTextViewID);
        amounOftEffect = (TextView) findViewById(R.id.selectedBuildingAmountOfEffectTextViewID);
        amounOftEffectByLv = (TextView) findViewById(R.id.selectedBuildingAmountOfEffectByLevelTextViewID);
        timeToBuild = (TextView) findViewById(R.id.selectedBuildingTimeToBuildTextViewID);
        timeToBuildByLv = (TextView) findViewById(R.id.selectedBuildingTimeToBuildByLevelTextViewID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Intent intent = getIntent();
        jsonBuilding = intent.getStringExtra("selectedBuilding");
        Gson gson = new Gson();
        building = gson.fromJson(jsonBuilding, Building.class);

        Glide.with(this).load(building.imageUrl).into(image);
        name.setText(building.name);
        level.setText(level.getText() + Integer.toString(building.level));
        effect.setText(effect.getText() + building.effect);
        gasCost.setText(gasCost.getText() + Integer.toString(building.gasCostLevel0));
        gasCostByLv.setText(gasCostByLv.getText() + Integer.toString(building.gasCostByLevel));
        mineralCost.setText(mineralCost.getText() + Integer.toString(building.mineralCostLevel0));
        mineralCostByLv.setText(mineralCostByLv.getText() + Integer.toString(building.mineralCostByLevel));
        amounOftEffect.setText(amounOftEffect.getText() + Integer.toString(building.amountOfEffectLevel0));
        amounOftEffectByLv.setText(amounOftEffectByLv.getText() + Integer.toString(building.amountOfEffectByLevel));
        timeToBuild.setText(timeToBuild.getText() + Integer.toString(building.timeToBuildLevel0));
        timeToBuildByLv.setText(timeToBuildByLv.getText() + Integer.toString(building.timeToBuildByLevel));

    }
}
