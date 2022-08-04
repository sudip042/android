package com.example.bookcarservicing.Controllers;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookcarservicing.R;
import com.squareup.picasso.Picasso;

public class ShowClickedParts extends AppCompatActivity {

    ImageView imgShowParts;
    TextView showPartsName, showPrice, showModel, showDescription;
    Button partsBook;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clicked_parts);

        actionBar = getSupportActionBar();
        actionBar.setTitle("BOOK PARTS");
        actionBar.setSubtitle("PARTS DETAILS");

        imgShowParts = findViewById(R.id.showpartsimage);
        showPartsName = findViewById(R.id.showpartsname);
        showPrice = findViewById(R.id.showprice);
        showDescription = findViewById(R.id.showdescription);

        partsBook = findViewById(R.id.bookparts);

        partsBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToBooking = new Intent(ShowClickedParts.this, BookParts.class ) ;
                startActivity(goToBooking);
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null) {
            showPartsName.setText(bundle.getString("Parts Name"));
            showPrice.setText(bundle.getString(" Price"));
            showModel.setText(bundle.getString("Model Name"));
            showDescription.setText(bundle.getString("Description"));

            String image = bundle.getString("Parts Image");
            Picasso.with(this).load(image).into(imgShowParts);
        }
    }
}
