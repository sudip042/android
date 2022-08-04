package com.example.bookcarservicing.Controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookcarservicing.Interface.CarServicingAPI;
import com.example.bookcarservicing.Model.Cars;
import com.example.bookcarservicing.Model.Users;
import com.example.bookcarservicing.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookServicing extends AppCompatActivity {
    EditText Address, phone, dateselection, user_id, u_name;
    Button btnBookNow;
    Spinner serviceLocation,carselection;
    CarServicingAPI carServicingAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_servicing);

        Address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        dateselection = findViewById(R.id.dateselection);
        carselection = findViewById(R.id.carselection);
        serviceLocation = findViewById(R.id.serviceLocation);
        user_id = findViewById(R.id.user_id);
        u_name = findViewById(R.id.name);

        btnBookNow = findViewById(R.id.btnBookNow);

        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(dateselection.getText().toString())){
                    Toast.makeText(BookServicing.this, "Error in Operation!! Null value not accepted..", Toast.LENGTH_SHORT).show();
                    dateselection.setError("Please choose the date");
                    dateselection.requestFocus();
                }else{
                    addOrder();
                }


            }
        });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:6060/")
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        carServicingAPI = retrofit.create(CarServicingAPI.class);

        SharedPreferences preferences = getSharedPreferences("UserData", 0);
        String token = preferences.getString("token",null);
        Toast.makeText(BookServicing.this, "User ID"+token, Toast.LENGTH_SHORT).show();

        if(token==null){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        else{
        final Call<Users> userData = carServicingAPI.userProfile(token);
        userData.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users users = response.body();
                Toast.makeText(BookServicing.this, "Data"+users, Toast.LENGTH_SHORT).show();
                user_id.setText(users.get_id());
                phone.setText(users.getPhonenumber());
                Address.setText(users.getAddress());
                u_name.setText(users.getFullname());

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(BookServicing.this, "Error in User Login! Retry", Toast.LENGTH_SHORT).show();
            }
        });
        }
         
}

public void addOrder(){
        final String userid,serLoc,serCar, address, ph, date, userr;
        userid = user_id.getText().toString();
        serLoc = serviceLocation.getSelectedItem().toString();
        serCar = carselection.getSelectedItem().toString();
        address = Address.getText().toString();
        ph = phone.getText().toString();
        date = dateselection.getText().toString();
        userr = u_name.getText().toString();


//    Toast.makeText(BookServicing.this, "Data userid"+userid+serLoc+userr, Toast.LENGTH_SHORT).show();

        Call<String> bookservicing = carServicingAPI.Book(userid,userr,ph,address,serCar,date,serLoc);
        bookservicing.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(BookServicing.this, "Booked on: "+date, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(BookServicing.this, "Booked on: "+date, Toast.LENGTH_SHORT).show();
            }
        });
}
}
