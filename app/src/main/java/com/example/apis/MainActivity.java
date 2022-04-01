package com.example.apis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apis.apii.RetrofitAPI;
import com.example.apis.constants.AppConstants;
import com.example.apis.databinding.ActivityMainBinding;
import com.example.apis.pojos.PostModal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
     private ActivityMainBinding activityMainBinding;
     //variables to store users input
    String name,job;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);



        activityMainBinding.idBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get user details
                name = activityMainBinding.idEdtName.getText().toString().trim();
                job = activityMainBinding.idEdtJob.getText().toString().trim();

               //validation steps
               if (name.isEmpty() || job.isEmpty()){
                   Toast.makeText(MainActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
               } else {
                   postData(name,job);
               }
            }
        });

        activityMainBinding.btnGoToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecyclerRetro.class);
                startActivity(intent);
            }
        });


    }

    public void postData(String name, String job){
        //having the progress bar loading
        activityMainBinding.idLoadingPB.setVisibility(View.VISIBLE);
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URLPOST)
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        PostModal modal = new PostModal(name, job);

        // calling a method to create a post and passing our modal class.
        Call<PostModal> call = retrofitAPI.createPost(modal);

        //executing our methods
        call.enqueue(new Callback<PostModal>() {
            @Override
            public void onResponse(Call<PostModal> call, Response<PostModal> response) {
                // this method is called when we get response from our api.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                activityMainBinding.idLoadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                activityMainBinding.idEdtName.setText("");
                activityMainBinding.idEdtJob.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                PostModal responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Job : " + responseFromAPI.getJob();

                // below line we are setting our
                // string to our text view.
                activityMainBinding.idTVResponse.setText(responseString);
            }

            @Override
            public void onFailure(Call<PostModal> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                activityMainBinding.idTVResponse.setText("Error found is : " + t.getMessage());
            }
        });
    }
}