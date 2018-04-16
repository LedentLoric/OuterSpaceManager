package com.example.lledent.outerspacemanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lledent on 16/01/2018.
 */

public interface OuterSpaceManagerService {
    @POST("api/v1/auth/create")
    Call<ConnectionResponse> createUser(@Body User user);

    @POST("api/v1/auth/login")
    Call<ConnectionResponse> connectUser(@Body User user);

    @GET("api/v1/users/get")
    Call<CurrentUserResponse> getCurrentUser(@Header("x-access-token") String token);

    @GET("api/v1/buildings/list")
    Call<BuildingsListResponse> getBuildingsList(@Header("x-access-token") String token);

    @POST("api/v1/buildings/create/{id}")
    Call<GetBuildingResponse> getBuilding(@Header("x-access-token") String token, @Path("id") int buildingID);

    @GET("api/v1/fleet/list")
    Call<ShipsListResponse> getFleetList(@Header("x-access-token") String token);

    @GET("api/v1/searches/list")
    Call<SearchesListResponse> getSearchesList(@Header("x-access-token") String token);

    @GET("api/v1/ships/")
    Call<ShipsListResponse> getShipsList(@Header("x-access-token") String token);

    @POST("api/v1/ships/create/{id}")
    Call<GetBuildingResponse> getShips(@Header("x-access-token") String token, @Path("id") int shipID, @Body ShipCreateRequest ship);

    @GET("api/v1/users/{from}/{limit}")
    Call<UserListResponse> getUserList(@Header("x-access-token") String token, @Path("from") int from, @Path("limit") int limit);
}
