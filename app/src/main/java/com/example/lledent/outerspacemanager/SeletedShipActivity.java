package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by lledent on 09/04/2018.
 */

public class SeletedShipActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private String token;

    private TextView selectedShipName;
    private TextView selectedShipLife;
    private TextView selectedShipAttack;
    private TextView selectedShipShield;
    private TextView selectedShipSpeed;
    private TextView selectedShipGasCost;
    private TextView selectedShipMineralCost;
    private TextView selectedShipTimeToBuild;
    private TextView selectedShipSpatioLevel;

    private SeekBar seekBar;
    private Button addButton;

    private String jsonShip;
    private Ship ship;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_ship_activity);

        selectedShipName = (TextView) findViewById(R.id.selectedShipNameTextViewID);
        selectedShipLife = (TextView) findViewById(R.id.selectedShipLifeTextViewID);
        selectedShipAttack = (TextView) findViewById(R.id.selectedShipAttackTextViewID);
        selectedShipShield = (TextView) findViewById(R.id.selectedShipShieldTextViewID);
        selectedShipSpeed = (TextView) findViewById(R.id.selectedShipSpeedTextViewID);
        selectedShipGasCost = (TextView) findViewById(R.id.selectedShipGasCostTextViewID);
        selectedShipMineralCost = (TextView) findViewById(R.id.selectedShipMineralCostViewID);
        selectedShipTimeToBuild = (TextView) findViewById(R.id.selectedShipTimeToBuildTextViewID);
        selectedShipSpatioLevel = (TextView) findViewById(R.id.selectedShipSpatioLevelTextViewID);

        seekBar = (SeekBar) findViewById(R.id.selectedShipSeekBarID);
        addButton = (Button) findViewById(R.id.selectedShipAddButtonID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Intent intent = getIntent();
        jsonShip = intent.getStringExtra("selectedShip");
        Gson gson = new Gson();
        ship = gson.fromJson(jsonShip, Ship.class);

        selectedShipName.setText(ship.name);
        selectedShipLife.setText(selectedShipLife.getText() + Integer.toString(ship.life));
        selectedShipAttack.setText(selectedShipAttack.getText() + Integer.toString(ship.minAttack) + " - " + ship.maxAttack);
        selectedShipShield.setText(selectedShipShield.getText() + Integer.toString(ship.shield));
        selectedShipSpeed.setText(selectedShipSpeed.getText() + Integer.toString(ship.speed));
        selectedShipGasCost.setText(selectedShipGasCost.getText() + Integer.toString(ship.gasCost));
        selectedShipMineralCost.setText(selectedShipMineralCost.getText() + Integer.toString(ship.mineralCost));
        selectedShipTimeToBuild.setText(selectedShipTimeToBuild.getText() + Integer.toString(ship.timeToBuild));
        selectedShipSpatioLevel.setText(selectedShipSpatioLevel.getText() + Integer.toString(ship.spatioportLevelNeeded));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int minValue = 1;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= minValue) {
                    seekBar.setProgress(minValue);
                    addButton.setText("Add 1 to my fleet");

                } else {
                    addButton.setText("Add " + Integer.toString(progress) + " to my fleet");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
                ShipCreateRequest shipToCreate = new ShipCreateRequest(ship.shipId, seekBar.getProgress());
                Call<GetBuildingResponse> request = service.getShips(token, ship.shipId, shipToCreate);

                request.enqueue(new Callback<GetBuildingResponse>() {
                    @Override
                    public void onResponse(Call<GetBuildingResponse> call, Response<GetBuildingResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Acquired ships !", Toast.LENGTH_SHORT).show();
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
