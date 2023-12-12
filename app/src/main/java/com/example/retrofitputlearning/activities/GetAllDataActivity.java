package com.example.retrofitputlearning.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.retrofitputlearning.R;
import com.example.retrofitputlearning.adapter.GettingAllDataRecyclerAdapter;
import com.example.retrofitputlearning.api.RetrofitAPI_NetworkCall;
import com.example.retrofitputlearning.models.DataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAllDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GettingAllDataRecyclerAdapter adapter;
    List<DataModel> listDataModelsEntities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_data);


        listDataModelsEntities = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

        getAllUsers();


    }
    void getAllUsers() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitAPI_NetworkCall.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitAPI_NetworkCall networkCall = retrofit.create(RetrofitAPI_NetworkCall.class);
        Call<List<DataModel>> call = networkCall.getAllData();

        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        listDataModelsEntities = response.body();

                        for (int i = 0; i < listDataModelsEntities.size(); i++) {

                            adapter = new GettingAllDataRecyclerAdapter(getApplicationContext(), listDataModelsEntities);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), CreateUserActivity.class));
        finish();
        super.onBackPressed();
    }
}