package com.example.bookcarservicing.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookcarservicing.Interface.CarServicingAPI;
import com.example.bookcarservicing.Model.AuthToken;
import com.example.bookcarservicing.R;

import Broadcasting.BroadCasting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity  {
    EditText username, password;
    Button login1;
    TextView tv_register;
    CarServicingAPI CarServicingAPI;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SensorManager sensorManager;
    BroadCasting broadCasting = new BroadCasting(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);

        login1 = findViewById(R.id.btn_login);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Login.this,Dashboard.class);
//                startActivity(intent);
                login();
            }
        });
        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, Registration.class);
                startActivity(registerIntent);
            }
        });

        proximity();


    }


    public void login() {

        switch (login1.getId()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Please enter your username");
                    username.requestFocus();
                    Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);
                    return;
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                    Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);
                    return;
                } else {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:6060/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    CarServicingAPI = retrofit.create(CarServicingAPI.class);

                    Call<AuthToken> checkLogin = CarServicingAPI.loginCheck(username.getText().toString(), password.getText().toString());

                    checkLogin.enqueue(new Callback<AuthToken>() {
                        @Override
                        public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(Login.this, "Error here.." + response.code(), Toast.LENGTH_LONG).show();
                                return;
                            }
                            preferences = (Login.this).getSharedPreferences("UserData", 0);
                            editor = preferences.edit();
                            AuthToken data = response.body();
                            editor.putString("token", data.getToken());
                            editor.putString("uid", data.getId());
                            //  editor.putString("uid", data.getUsers().get_id());
                            //  Toast.makeText(Login.this, "User id is : " + data.getUsers().get_id(), Toast.LENGTH_SHORT).show();
                            editor.commit();

                            Toast.makeText(Login.this, "Successfully Logged In" , Toast.LENGTH_SHORT).show();
                            Intent openDashboard = new Intent(Login.this, Dashboard.class);
                            startActivity(openDashboard);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<AuthToken> call, Throwable t) {
                            Toast.makeText(Login.this, "ERROR 1" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCasting, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCasting);
    }

    public void proximity() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensor == null) {
            Toast.makeText(this, "No sensor detected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sensor Kicking in .....", Toast.LENGTH_SHORT).show();
        }

        SensorEventListener proximityListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                WindowManager.LayoutParams params = Login.this.getWindow().getAttributes();
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] == 0) {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = 0;
                        getWindow().setAttributes(params);
                    } else {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = -1f;
                        getWindow().setAttributes(params);
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(proximityListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

}




