package com.example.bookcarservicing.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookcarservicing.Model.Cars;
import com.example.bookcarservicing.R;
import com.squareup.picasso.Picasso;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.carViewHolder> {
    List<Cars> carList;
    Context context;
    Bitmap bitmap;
    ;

    public static final String BASE_URL = "http://10.0.2.2:6060/";


    public CarAdapter(Context context, List<Cars> carList) {
        this.context = context;
        this.carList = carList;

    }

    @NonNull
    @Override
    public carViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.car_row, viewGroup, false );
        return new carViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull carViewHolder carViewHolder, int i) {
        final Cars car = carList.get(i);

        carViewHolder.Tyre.setText("Tyre: " + car.getTyre());
        carViewHolder.Manufacturer.setText("Manufacturer: " + car.getManufacturer());
        carViewHolder.Model.setText("Model: " + car.getModel());
        carViewHolder.Assembly.setText("Assembly: " + car.getAssembly());
        carViewHolder.Power.setText("Power: " + car.getPower());
        carViewHolder.Fuel.setText("Fuel: " + car.getFuel());
        carViewHolder.Abs.setText("Abs: " + car.getAbs());
        carViewHolder.Wheels.setText("Wheels: " + car.getWheels());
        carViewHolder.Engine.setText("Engine: " + car.getEngine());
        carViewHolder.Mileage.setText("Mileage: " + car.getMileage());

        Picasso.with(context).load(BASE_URL + "Cars/" + car.getCarimg()).into(carViewHolder.Imgcar);
        Log.d("log", "OnBindHolder:" + BASE_URL + "images/" + car.getCarname());
        Toast.makeText(context, "" + car.getCarname(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class carViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView Imgcar;
        public TextView Tyre, Assembly, Model, Manufacturer,Engine,Wheels,Abs,Mileage,Fuel,Power;
        public Button viewMore;

        public carViewHolder(@NonNull View itemView) {
            super(itemView);
            Imgcar = itemView.findViewById(R.id.imgcar);
            Tyre = itemView.findViewById(R.id.tyre);
            Assembly = itemView.findViewById(R.id.assembly);
            Model = itemView.findViewById(R.id.model);
            Manufacturer = itemView.findViewById(R.id.manufacturer);
            Engine = itemView.findViewById(R.id.engine);
            Wheels = itemView.findViewById(R.id.wheels);
            Abs = itemView.findViewById(R.id.abs);
            Mileage = itemView.findViewById(R.id.mileage);
            Fuel = itemView.findViewById(R.id.fuel);
            Power = itemView.findViewById(R.id.power);
        }
    }
}
