package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lledent on 23/01/2018.
 */

public class BuildingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private ListView buildingListView;

    private String token;

    private Building selectedBuilding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_activity);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BuildingsListFragment fraBuildingsList = (BuildingsListFragment) getSupportFragmentManager().findFragmentById(R.id.buildingBuildingListFragmentID);
        SelectedBuildingFragment fraSelectedBuilding = (SelectedBuildingFragment) getSupportFragmentManager().findFragmentById(R.id.buildingSelectedBuildingFragmentID);

        selectedBuilding = fraBuildingsList.apiBuildingsList.get(position);
        Gson gson = new Gson();
        String jsonSelectedBuilding = gson.toJson(selectedBuilding);

        if(fraSelectedBuilding == null|| !fraSelectedBuilding.isInLayout()){
            Intent selectBuildingActivity = new Intent(getApplicationContext(), SelectedBuildingActivity.class);
            selectBuildingActivity.putExtra("selectedBuilding", jsonSelectedBuilding);
            startActivity(selectBuildingActivity);
        } else {
            fraSelectedBuilding.fillSelectedBuildingFragment(jsonSelectedBuilding);
        }
    }
}
