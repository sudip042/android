package com.example.bookcarservicing.Controllers;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookcarservicing.Interface.CarServicingAPI;
import com.example.bookcarservicing.Model.Users;
import com.example.bookcarservicing.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateUsers extends AppCompatActivity {
    String fullname1, email1, phonenumber1, address1, username1;
    EditText fullname, email, phonenumber, address, username;
    Button btnupdateuser;
    CarServicingAPI carServicingAPI;
    String userid, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_users);

        fullname = findViewById(R.id.edittextfullname);
        email = findViewById(R.id.edittextemail);
        phonenumber = findViewById(R.id.edittextphonenumber);
        address = findViewById(R.id.edittextaddress);
        username = findViewById(R.id.edittextusername);

        btnupdateuser = findViewById(R.id.buttonupdate);
        loadUserData();

        btnupdateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    private void loadUserData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:6060/")
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        carServicingAPI = retrofit.create(CarServicingAPI.class);

        SharedPreferences preferences = getSharedPreferences("UserData", 0);
        token = preferences.getString("token", null);
        Toast.makeText(UpdateUsers.this, "User ID", Toast.LENGTH_SHORT).show();

        final Call<Users> userData = carServicingAPI.userProfile(token);

        userData.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users users = response.body();
                fullname.setText(users.getFullname());
                email.setText(users.getEmail());
                phonenumber.setText(users.getPhonenumber());
                address.setText(users.getAddress());
                username.setText(users.getUsername());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(UpdateUsers.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateProfile() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:6060")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        carServicingAPI = retrofit.create(CarServicingAPI.class);


        fullname1 = fullname.getText().toString();
        email1 = email.getText().toString();
        phonenumber1 = phonenumber.getText().toString();
        address1 = address.getText().toString();
        username1 = username.getText().toString();

        SharedPreferences preferences = (UpdateUsers.this).getSharedPreferences("User Data", 0);
        String userId = preferences.getString("uid", "");
        Toast.makeText(this, "User ID+" + userId, Toast.LENGTH_SHORT).show();

        Call<String> updateProfileData = carServicingAPI.updateProfile(userId, fullname1, email1, phonenumber1, address1, username1);
        updateProfileData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(UpdateUsers.this, "User Profile Updates", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(UpdateUsers.this, "ERROR IN UPDATING" + t.getMessage(), Toast.LENGTH_SHORT);

            }
        });
    }
}