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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lledent on 23/01/2018.
 */

public class MainActivity extends AppCompatActivity {
    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private TextView username;
    private TextView points;

    private Button overviewButton;
    private Button buildingButton;
    private Button fleetButton;
    private Button searchesButton;
    private Button spaceButton;
    private Button galaxyButton;
    private Button logoutButton;

    private String token;

    private CurrentUserResponse user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        username = (TextView) findViewById(R.id.mainUsernameTextViewID);
        points = (TextView) findViewById(R.id.mainPointTextViewID);
        overviewButton = (Button) findViewById(R.id.mainOverviewButtonID);
        buildingButton = (Button) findViewById(R.id.mainBuildingButtonID);
        fleetButton = (Button) findViewById(R.id.mainFleetButtonID);
        searchesButton = (Button) findViewById(R.id.mainSearchesButtonID);
        spaceButton = (Button) findViewById(R.id.mainSpaceButtonID);
        galaxyButton = (Button) findViewById(R.id.mainGalaxyButtonID);
        logoutButton = (Button) findViewById(R.id.mainLogoutButtonID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
        Call<CurrentUserResponse> request = service.getCurrentUser(token);
        request.enqueue(new Callback<CurrentUserResponse>(){
            @Override
            public void onResponse(Call<CurrentUserResponse> call, Response<CurrentUserResponse> response) {
                if (response.code() == 200) {
                    user = response.body();
                    username.setText(user.username);
                    points.setText("Points : " + Integer.toString(user.points));

                } else if (response.code() == 403) {
                    settings.edit().remove("token").commit();

                    Toast.makeText(getApplicationContext(),"Votre accès a expiré",Toast.LENGTH_SHORT).show();
                    Intent signInActivity = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(signInActivity);
                }
            }

            @Override
            public void onFailure(Call<CurrentUserResponse> call, Throwable t) {

            }
        });

        // OVERVIEW
        overviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String jsonUser = gson.toJson(user);
                Intent overviewActivity = new Intent(getApplicationContext(), OverviewActivity.class);
                overviewActivity.putExtra("currentUser", jsonUser);
                startActivity(overviewActivity);
            }
        });

        // BUILDING
        buildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buildingActivity = new Intent(getApplicationContext(), BuildingActivity.class);
                startActivity(buildingActivity);
            }
        });

        // FLEET
        fleetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fleetActivity = new Intent(getApplicationContext(), FleetActivity.class);
                startActivity(fleetActivity);
            }
        });

        // LOGOUT
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().remove("token").commit();

                Toast.makeText(getApplicationContext(),"Vous avez été déconnecté",Toast.LENGTH_SHORT).show();
                Intent signInActivity = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(signInActivity);
            }
        });

    }
}
