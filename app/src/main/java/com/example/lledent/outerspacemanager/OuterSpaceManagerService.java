package com.example.lledent.outerspacemanager;

import retrofit2.Call;
import retrofit2.http.Body;
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
}
