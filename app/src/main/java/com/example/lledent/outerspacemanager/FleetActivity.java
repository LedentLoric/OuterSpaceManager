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
 * Created by lledent on 16/04/2018.
 */

public class FleetActivity extends AppCompatActivity {

    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private String token;

    private ListView fleetListView;
    private List<Ship> apiFleetList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fleet_activity);

        fleetListView = (ListView) findViewById(R.id.fleetListViewID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://outer-space-manager.herokuapp.com")
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
        Call<ShipsListResponse> request = service.getFleetList(token);

        request.enqueue(new Callback<ShipsListResponse>() {
            @Override
            public void onResponse(Call<ShipsListResponse> call, Response<ShipsListResponse> response) {
                if (response.code() == 200) {
                    apiFleetList = response.body().ships;
                    fleetListView.setAdapter(new FleetArrayAdapter(getApplicationContext(), apiFleetList));
                }
            }

            @Override
            public void onFailure(Call<ShipsListResponse> call, Throwable t) {

            }
        });
    }
}
