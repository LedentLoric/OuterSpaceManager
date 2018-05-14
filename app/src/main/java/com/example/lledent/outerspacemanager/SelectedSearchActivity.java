package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    private Button getSearchBtn;

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
        getSearchBtn = findViewById(R.id.selectedSearchesGetSearchBtnID);

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

        getSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
                Call<GetBuildingResponse> request = service.getSearch(token, search.getSearchId());

                request.enqueue(new Callback<GetBuildingResponse>() {
                    @Override
                    public void onResponse(Call<GetBuildingResponse> call, Response<GetBuildingResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Added to searches !", Toast.LENGTH_SHORT).show();
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
