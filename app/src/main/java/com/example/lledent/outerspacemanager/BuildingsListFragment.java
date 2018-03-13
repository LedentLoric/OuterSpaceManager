package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by lledent on 13/03/2018.
 */

public class BuildingsListFragment extends Fragment {
    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private ListView buildingListView;

    private String token;

    public List<Building> apiBuildingsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_building_list,container);
        buildingListView = (ListView) v.findViewById(R.id.buildingListViewID);
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
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
                    apiBuildingsList = response.body().buildings;
                    buildingListView.setAdapter(new BuildingArrayAdapter(getContext(), response.body().buildings));
                    buildingListView.setOnItemClickListener((BuildingActivity) getActivity());
                }
            }

            @Override
            public void onFailure(Call<BuildingsListResponse> call, Throwable t) {

            }
        });
    }
}
