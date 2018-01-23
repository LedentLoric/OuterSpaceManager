package com.example.lledent.outerspacemanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by lledent on 23/01/2018.
 */

public class MainActivity extends Activity {
    private TextView username;
    private TextView points;

    private Button overviewButton;
    private Button buildingButton;
    private Button fleetButton;
    private Button searchesButton;
    private Button spaceButton;
    private Button galaxyButton;

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
        
    }
}
