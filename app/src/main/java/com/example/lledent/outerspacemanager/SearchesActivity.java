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

public class SearchesActivity extends AppCompatActivity {

    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private String token;

    private ListView searchListView;
    private List<Search> apiSearchesList;
    private Search selectedSearch;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searches_activity);

        searchListView = (ListView) findViewById(R.id.searchesListViewID);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        token = settings.getString("token", "");

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://outer-space-manager.herokuapp.com")
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
        Call<SearchesListResponse> request = service.getSearchesList(token);

        request.enqueue(new Callback<SearchesListResponse>() {
            @Override
            public void onResponse(Call<SearchesListResponse> call, Response<SearchesListResponse> response) {
                if (response.code() == 200) {
                    apiSearchesList = response.body().getSearches();
                    searchListView.setAdapter(new SearchesArrayAdapter(getApplicationContext(), apiSearchesList));
                    searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            selectedSearch = apiSearchesList.get(position);

                            Gson gson = new Gson();
                            String jsonSelectedSearch = gson.toJson(selectedSearch);

                            Intent selectShipActivity = new Intent(getApplicationContext(), SeletedShipActivity.class);
                            selectShipActivity.putExtra("selectedSearch", jsonSelectedSearch);
                            startActivity(selectShipActivity);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<SearchesListResponse> call, Throwable t) {

            }
        });
    }
}
