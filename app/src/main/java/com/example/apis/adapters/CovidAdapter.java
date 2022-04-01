package com.example.apis.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apis.R;
import com.example.apis.pojos.Countries;

import java.util.ArrayList;

public class CovidAdapter extends RecyclerView.Adapter<CovidAdapter.ViewHolder> {

    //modal reference
    private ArrayList<Countries> countries;
    Context context;

    public CovidAdapter(ArrayList<Countries> countries, Context context)
    {
        this.countries = countries;
        this.context = context;
    }

    //use this method to access your recycler layout
    @NonNull
    @Override
    public CovidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new ViewHolder(view);
    }

    //interact with the views inside the layout
    @Override
    public void onBindViewHolder(@NonNull CovidAdapter.ViewHolder holder, int position) {
        Countries country_position = countries.get(position);
        holder.country_Name.setText(country_position.getCountry());
        holder.confirmed_Cases.setText(country_position.getTotalConfirmed());
        holder.re_coveries.setText(country_position.getTotalRecovered());

        //onclick
        holder.country_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Position of item is " + position, Toast.LENGTH_SHORT).show();
                //activate this part
//                Intent intent = new Intent(context,DetailedClass.class);
//                intent.putExtra("countryName", country_position.getCountry());
//                intent.putExtra("countryCode", country_position.getCountryCode());
//                context.startActivity(intent);
            }
        });
    }

    //return the size of our pojo //simply the size of the response
    @Override
    public int getItemCount() {
        return countries.size();
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView country_Name,confirmed_Cases,re_coveries;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            country_Name = itemView.findViewById(R.id.countryName);
            confirmed_Cases = itemView.findViewById(R.id.confirmedCases);
            re_coveries = itemView.findViewById(R.id.recovery);
        }
    }
}
