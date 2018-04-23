package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by lledent on 16/04/2018.
 */

public class SelectedSearchActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private String token;

    private TextView name;
    private TextView effect;
    private TextView level;
    private TextView amountOfEffect;
    private TextView gasCost;
    private TextView mineralCost;
    private TextView gasCostByLv;
    private TextView mineralCostByLv;

    private String jsonSearch;
    private Search search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_search_activity);

        name = findViewById(R.id.selectedSearchNameTextViewID);
        effect = findViewById(R.id.selectedSearchEffectTextViewID);
        level = findViewById(R.id.selectedSearchLevelTextViewID);
        amountOfEffect = findViewById(R.id.selectedSearchEffectByLevelTextViewID);
        gasCost = findViewById(R.id.selectedSearchGasCostLv0TextViewID);
        mineralCost = findViewById(R.id.selectedSearchMineralCostLv0TextViewID);
        gasCostByLv = findViewById(R.id.selectedSearchGasCostByLvTextViewID);
        mineralCostByLv = findViewById(R.id.selectedSearchMineralCostByLvTextViewID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Intent intent = getIntent();
        jsonSearch = intent.getStringExtra("selectedSearch");
        Gson gson = new Gson();
        search = gson.fromJson(jsonSearch, Search.class);

        name.setText(search.getName());
        effect.setText(effect.getText() + search.getEffect());
        level.setText(level.getText() + Integer.toString(search.getLevel()));
        amountOfEffect.setText(amountOfEffect.getText() + Integer.toString(search.getAmountOfEffectByLevel()));
        gasCost.setText(gasCost.getText() + Integer.toString(search.getGasCostLevel0()));
        mineralCost.setText(mineralCost.getText() + Integer.toString(search.getMineralCostLevel0()));
        gasCostByLv.setText(gasCostByLv.getText() + Integer.toString(search.getGasCostByLevel()));
        mineralCostByLv.setText(mineralCostByLv.getText() + Integer.toString(search.getMineralCostByLevel()));
    }
}
