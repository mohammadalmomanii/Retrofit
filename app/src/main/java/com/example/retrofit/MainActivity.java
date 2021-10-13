package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.retrofit.RESTApi.ApiServer;
import com.example.retrofit.RESTApi.model;
import com.example.retrofit.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvTest.setText("yes");



        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer server=retrofit.create(ApiServer.class);

        Call<model> call=server.getUser();
        call.enqueue(new Callback<model>() {
            @Override
            public void onResponse(Call<model> call, Response<model> response) {
                binding.tvTest.setText(response.body().getBody());
            }

            @Override
            public void onFailure(Call<model> call, Throwable t) {
                binding.tvTest.setText(t.getMessage());
            }
        });


        Call<List<model>> listCall=server.getUsers("5");
        listCall.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                binding.tvTest.setText(response.body().get(2).getBody());
            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {

            }
        });
        model model=new model(4,"myTest","test test test test test test test test ");
        Call<model> call1=server.setUser(model);

        call1.enqueue(new Callback<com.example.retrofit.RESTApi.model>() {
            @Override
            public void onResponse(Call<model> call, Response<model> response) {
                binding.tvTest.setText(response.body().getBody());
            }

            @Override
            public void onFailure(Call<com.example.retrofit.RESTApi.model> call, Throwable t) {

            }
        });
    }
}