package com.example.lledent.outerspacemanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lledent on 16/04/2018.
 */

public class GalaxyActivity extends AppCompatActivity {
    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private String token;

    private ListView userListView;
    private List<UserScore> apiUserList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galaxy_activity);

        userListView = (ListView) findViewById(R.id.userListViewID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://outer-space-manager.herokuapp.com")
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
        Call<UserListResponse> request = service.getUserList(token, 0, 20);

        request.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                if (response.code() == 200) {
                    apiUserList = response.body().users;
                    userListView.setAdapter(new UserArrayAdapter(getApplicationContext(), apiUserList));
//                    fleetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            selectedShip = apiShipsList.get(position);
//
//                            Gson gson = new Gson();
//                            String jsonSelectedShip = gson.toJson(selectedShip);
//
//                            Intent selectShipActivity = new Intent(getApplicationContext(), SeletedShipActivity.class);
//                            selectShipActivity.putExtra("selectedShip", jsonSelectedShip);
//                            startActivity(selectShipActivity);
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {

            }
        });
    }
}
