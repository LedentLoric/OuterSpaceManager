package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private Button getBuildingBtn;

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
        getBuildingBtn = (Button) findViewById(R.id.selectedBuildingGetBuildingBtnID);

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

        getBuildingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
                Call<GetBuildingResponse> request = service.getBuilding(token, building.buildingId);

                request.enqueue(new Callback<GetBuildingResponse>() {
                    @Override
                    public void onResponse(Call<GetBuildingResponse> call, Response<GetBuildingResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Building acquit", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject errorResponse = new JSONObject(response.errorBody().string());
                                Toast.makeText(getApplicationContext(), errorResponse.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<GetBuildingResponse> call, Throwable t) {

                    }
                });
            }
        });

    }
}
