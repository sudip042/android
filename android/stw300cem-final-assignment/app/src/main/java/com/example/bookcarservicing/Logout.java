package com.example.bookcarservicing;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookcarservicing.Controllers.Login;

public class Logout extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView txtView;
    Switch btnSensor;
    SensorEventListener  gryoListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        btnSensor = findViewById(R.id.Switch);
        btnSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorGyro();
            }
        });
    }
    private void sensorGyro(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gryoListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(btnSensor.isChecked()){
                    if(sensorEvent.values[1]<0){
                        Toast.makeText(Logout.this, "Successfully Logout!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Logout.this, Login.class);
                        startActivity(intent);

                    }else if(sensorEvent.values[1]>0) {
                        btnSensor.setChecked(false);
                    }
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        if(sensor!= null){
            sensorManager.registerListener(gryoListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        }else {
            Toast.makeText(Logout.this, "Sensor is not found", Toast.LENGTH_SHORT).show();
        }
    }

}
