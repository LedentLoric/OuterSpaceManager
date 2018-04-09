package com.example.lledent.outerspacemanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lledent on 09/04/2018.
 */

public class FleetActivity extends AppCompatActivity {

    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private String token;

    private ListView shipsListView;
    private List<Ship> apiShipsList;
    private Ship selectedShip;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fleet_activity);

        shipsListView = (ListView) findViewById(R.id.shipsListViewID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
        Call<ShipsListResponse> request = service.getShipsList(token);

        request.enqueue(new Callback<ShipsListResponse>() {
            @Override
            public void onResponse(Call<ShipsListResponse> call, Response<ShipsListResponse> response) {
                if (response.code() == 200) {
                    apiShipsList = response.body().ships;
                    shipsListView.setAdapter(new ShipArrayAdapter(getApplicationContext(), apiShipsList));
                    shipsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            selectedShip = apiShipsList.get(position);

                            Gson gson = new Gson();
                            String jsonSelectedShip = gson.toJson(selectedShip);
                            Toast.makeText(getApplicationContext(),jsonSelectedShip,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ShipsListResponse> call, Throwable t) {

            }
        });
    }
}
