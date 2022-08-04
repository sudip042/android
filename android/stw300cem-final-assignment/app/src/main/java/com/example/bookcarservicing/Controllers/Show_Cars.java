package com.example.bookcarservicing.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bookcarservicing.Adapter.CarAdapter;
import com.example.bookcarservicing.Interface.CarServicingAPI;
import com.example.bookcarservicing.Model.Cars;
import com.example.bookcarservicing.R;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Show_Cars extends AppCompatActivity {
    private RecyclerView carrecyclerView;
    List<Cars> carList = new ArrayList<>();
    Retrofit retrofit;
    CarServicingAPI carServicingAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__cars);

        carrecyclerView = findViewById(R.id.carrecyclerView);
        getCars();
//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

    }

    public void getCars() {



        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:6060/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        carServicingAPI = retrofit.create(CarServicingAPI.class);

        Call<List<Cars>> listCall = carServicingAPI.getCar();
        listCall.enqueue(new Callback<List<Cars>>() {
            @Override
            public void onResponse(Call<List<Cars>> call, Response<List<Cars>> response) {
                carList = response.body();

                CarAdapter carAdapter = new CarAdapter(Show_Cars.this, carList);
//                carrecyclerView.setAdapter(new CarAdapter (Show_Cars.this, carList));
                carrecyclerView.setAdapter(carAdapter);
                carrecyclerView.setLayoutManager(new LinearLayoutManager(Show_Cars.this));
            }

            @Override
            public void onFailure(Call<List<Cars>> call, Throwable t) {
                Toast.makeText(Show_Cars.this, "ERROR" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
