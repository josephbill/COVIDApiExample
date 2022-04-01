package com.example.apis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apis.adapters.CovidAdapter;
import com.example.apis.apii.JSONResponse;
import com.example.apis.apii.RetrofitAPI;
import com.example.apis.constants.AppConstants;
import com.example.apis.databinding.ActivityMainBinding;
import com.example.apis.databinding.ActivityRecyclerRetroBinding;
import com.example.apis.pojos.Countries;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerRetro extends AppCompatActivity {
    private ArrayList<Countries> countries;
    private CovidAdapter adapter;
    private ActivityRecyclerRetroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerRetroBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initViews();
    }

    private void initViews() {
        binding.recyclerCovid.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerCovid.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URLGET)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI request = retrofit.create(RetrofitAPI.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();  //response from retrofit call
                assert jsonResponse != null; //validating that our response is not blank
                countries = new ArrayList<>(Arrays.asList(jsonResponse.getCountries()));
                adapter = new CovidAdapter(countries,RecyclerRetro.this);
                binding.recyclerCovid.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Toast.makeText(RecyclerRetro.this, "Error is "  + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}