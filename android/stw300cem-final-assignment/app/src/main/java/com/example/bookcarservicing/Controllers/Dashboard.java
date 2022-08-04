package com.example.bookcarservicing.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bookcarservicing.Logout;
import com.example.bookcarservicing.R;

public class Dashboard extends AppCompatActivity {

    Button buttonUser, buttonParts, buttonBooking, buttoncars, buttonlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        buttonUser = findViewById(R.id.dashboardbutton_users);
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this,UpdateUsers.class);
                startActivity(intent);
            }
        });

        buttonParts = findViewById(R.id.dashboardbutton_parts);
        buttonParts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Show_Parts.class);
                startActivity(intent);
            }
        });

        buttonBooking = findViewById(R.id.dashboardbutton_booking);
        buttonBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, BookServicing.class);
                startActivity(intent);

            }
        });

        buttoncars = findViewById(R.id.dashboardbutton_cars);
        buttoncars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Show_Cars.class);
                startActivity(intent);
            }
        });

        buttonlogout = findViewById(R.id.logout);
        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Logout.class);
                startActivity(intent);
            }
        });
    }
}
