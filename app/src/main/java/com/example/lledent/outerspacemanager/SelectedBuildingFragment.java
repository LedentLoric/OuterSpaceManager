package com.example.lledent.outerspacemanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
 * Created by lledent on 13/03/2018.
 */

public class SelectedBuildingFragment extends Fragment {

    public static final String PREFS_NAME = "MyPrefsFile";
    private String token;

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

    public void fillSelectedBuildingFragment(String jsonBuilding) {
        Gson gson = new Gson();
        building = gson.fromJson(jsonBuilding, Building.class);

        name.setText(building.name);
        level.setText("Level : " + Integer.toString(building.level));
        effect.setText("Effect : " + building.effect);
        gasCost.setText("Gas Cost : " + Integer.toString(building.gasCostLevel0));
        gasCostByLv.setText("Gas Cost (by lv.) : " + Integer.toString(building.gasCostByLevel));
        mineralCost.setText("Mineral Cost : " + Integer.toString(building.mineralCostLevel0));
        mineralCostByLv.setText("Mineral Cost (by lv.) : " + Integer.toString(building.mineralCostByLevel));
        amounOftEffect.setText("Amount of effect : " + Integer.toString(building.amountOfEffectLevel0));
        amounOftEffectByLv.setText("Amount of Effect (by lv.) : " + Integer.toString(building.amountOfEffectByLevel));
        timeToBuild.setText("Time to build : " + Integer.toString(building.timeToBuildLevel0));
        timeToBuildByLv.setText("Time to build (by lv.) : " + Integer.toString(building.timeToBuildByLevel));

        getBuildingBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_selected_building,container);
        name = (TextView) v.findViewById(R.id.fragmentSelectedBuildingNameTextViewID);
        level = (TextView) v.findViewById(R.id.fragmentSelectedBuildingLevelTextViewID);
        effect = (TextView) v.findViewById(R.id.fragmentSelectedBuildingEffectTextViewID);
        gasCost = (TextView) v.findViewById(R.id.fragmentSelectedBuildingGasCostTextViewID);
        gasCostByLv = (TextView) v.findViewById(R.id.fragmentSelectedBuildingGasCostByLevelTextViewID);
        mineralCost = (TextView) v.findViewById(R.id.fragmentSelectedBuildingMineralCostTextViewID);
        mineralCostByLv = (TextView) v.findViewById(R.id.fragmentSelectedBuildingMineralCostByLevelTextViewID);
        amounOftEffect = (TextView) v.findViewById(R.id.fragmentSelectedBuildingAmountOfEffectTextViewID);
        amounOftEffectByLv = (TextView) v.findViewById(R.id.fragmentSelectedBuildingAmountOfEffectByLevelTextViewID);
        timeToBuild = (TextView) v.findViewById(R.id.fragmentSelectedBuildingTimeToBuildTextViewID);
        timeToBuildByLv = (TextView) v.findViewById(R.id.fragmentSelectedBuildingTimeToBuildByLevelTextViewID);
        getBuildingBtn = (Button) v.findViewById(R.id.fragmentSelectedBuildingGetBuildingBtnID);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        getBuildingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
                Call<GetBuildingResponse> request = service.getBuilding(token, building.buildingId);

                request.enqueue(new Callback<GetBuildingResponse>() {
                    @Override
                    public void onResponse(Call<GetBuildingResponse> call, Response<GetBuildingResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getContext(), "Acquired building !", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject errorResponse = new JSONObject(response.errorBody().string());
                                Toast.makeText(getContext(), errorResponse.getString("message"), Toast.LENGTH_SHORT).show();
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
