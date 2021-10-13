package com.example.retrofit.RESTApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {
    @GET("posts/1")
    public Call<model> getUser();
    @GET("posts")
    public Call<List<model>> getUsers(@Query("postId") String postId);
    @POST("posts")
    public Call<model> setUser(@Body model model);
}
