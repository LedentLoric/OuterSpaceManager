package com.example.lledent.outerspacemanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lledent on 23/01/2018.
 */

public class BuildingActivity extends AppCompatActivity {
    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private ListView buildingListView;

    private String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_activity);
//        ActionBar toolbar = getSupportActionBar();
//        toolbar.setDisplayHomeAsUpEnabled(true);
        buildingListView = (ListView) findViewById(R.id.buildingListViewID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
        Call<BuildingsListResponse> request = service.getBuildingsList(token);

        request.enqueue(new Callback<BuildingsListResponse>() {
            @Override
            public void onResponse(Call<BuildingsListResponse> call, Response<BuildingsListResponse> response) {
                if (response.code() == 200) {
                    buildingListView.setAdapter(new BuildingArrayAdapter(getApplicationContext(), response.body().buildings));
                }
            }

            @Override
            public void onFailure(Call<BuildingsListResponse> call, Throwable t) {

            }
        });

    }
}
