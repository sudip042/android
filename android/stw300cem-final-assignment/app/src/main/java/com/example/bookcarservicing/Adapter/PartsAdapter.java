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

import com.example.bookcarservicing.Model.Parts;
import com.example.bookcarservicing.R;
import com.squareup.picasso.Picasso;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PartsAdapter extends RecyclerView.Adapter<PartsAdapter.PartsViewHolder> {
    List<Parts> partsList;
    Context context;
    Bitmap bitmap;
    ;

    public static final String BASE_URL = "http://10.0.2.2:6060/";

    public PartsAdapter(Context context, List<Parts> partsList) {
        this.context = context;
        this.partsList = partsList;

    }

    @NonNull
    @Override
    public PartsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.parts_row, viewGroup, false);
        return new PartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartsViewHolder partsViewHolder, int i) {
        final Parts parts = partsList.get(i);
//             Parts parts = partsList.get(i)
        partsViewHolder.PartsName.setText("Parts Name: " + parts.getPartsname());
        partsViewHolder.Price.setText("Price: " + parts.getPrice());
        partsViewHolder.Model.setText("Model: " + parts.getModel());
        partsViewHolder.Description.setText("Description: " + parts.getDescription());


        Picasso.with(context).load(BASE_URL + "parts/" + parts.getPartsimg()).into(partsViewHolder.ImgParts);
        Log.d("log", "OnBindHolder:" + BASE_URL + "images/" + parts.getPartsname());
        Toast.makeText(context, "" + parts.getPartsname(), Toast.LENGTH_SHORT).show();

//        partsViewHolder.viewMore.setOnCLickListner(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                Context vcontext = view.getContext();
//                String provide_path = BASE_URL+"/images/"+parts.getPartsname();
//
//                System.out.println(provide_path);
//
//                Intent showParts = new Intent(context, ShowClickedParts.class );
//                showParts.putExtra("Parts Name", parts.getPartsname());
//                showParts.putExtra("Price", parts.getPrice());
//                showParts.putExtra("Model", parts.getModel());
//                showParts.putExtra("Description", parts.getDescription());
//                showParts.putExtra("Parts Image", BASE_URL+"/images/"+parts.getImageId());
//
//                vcontext.startActivity(showParts);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return partsList.size();
    }

    public class PartsViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView ImgParts;
        public TextView PartsName, Price, Model, Description;
        public Button viewMore;

        public PartsViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgParts = itemView.findViewById(R.id.imgparts);
            PartsName = itemView.findViewById(R.id.partsname);
            Price = itemView.findViewById(R.id.price);
            Model = itemView.findViewById(R.id.model);
            Description = itemView.findViewById(R.id.description);
//            viewMore=itemView.findViewById(R.id.);

        }
    }
}
