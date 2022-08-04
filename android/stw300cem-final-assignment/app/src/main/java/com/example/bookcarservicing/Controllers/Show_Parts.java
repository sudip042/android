package com.example.bookcarservicing.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bookcarservicing.Interface.CarServicingAPI;
import com.example.bookcarservicing.Model.Parts;
import com.example.bookcarservicing.Adapter.PartsAdapter;
import com.example.bookcarservicing.R;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Show_Parts extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<Parts> partsList = new ArrayList<>();
    Retrofit retrofit;
    CarServicingAPI carServicingAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__parts);

        recyclerView = findViewById(R.id.recyclerView);
        getParts();
//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

    }

    public void getParts() {



        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:6060/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        carServicingAPI = retrofit.create(CarServicingAPI.class);

        Call<List<Parts>> listCall = carServicingAPI.getParts();
        listCall.enqueue(new Callback<List<Parts>>() {
            @Override
            public void onResponse(Call<List<Parts>> call, Response<List<Parts>> response) {
                partsList = response.body();
                recyclerView.setAdapter(new PartsAdapter(Show_Parts.this, partsList));
                PartsAdapter partsAdapter = new PartsAdapter(Show_Parts.this, partsList);
                recyclerView.setAdapter(partsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Show_Parts.this));
            }

            @Override
            public void onFailure(Call<List<Parts>> call, Throwable t) {
                Toast.makeText(Show_Parts.this, "ERROR" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
