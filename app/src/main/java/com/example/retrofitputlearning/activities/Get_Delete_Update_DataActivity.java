package com.example.retrofitputlearning.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitputlearning.R;
import com.example.retrofitputlearning.api.RetrofitAPI_NetworkCall;
import com.example.retrofitputlearning.models.DataModel;
import com.example.retrofitputlearning.models.Retrofit_ResponseModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Get_Delete_Update_DataActivity extends AppCompatActivity {

    TextView emailTextview, nameTextview, createUserTextView;
    Button deleteuserButton, updatebutton;
    TextInputEditText nameTxtInputEditTxt, emailTxtInputEditTxt;
    int id = 1;

    Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitAPI_NetworkCall.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    RetrofitAPI_NetworkCall networkCall = retrofit.create(RetrofitAPI_NetworkCall.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_delete_update_data);

        createUserTextView = findViewById(R.id.createUserTextView);
        emailTextview = findViewById(R.id.emailTextView);
        nameTextview = findViewById(R.id.nameTextView);
        deleteuserButton = findViewById(R.id.deleteButton);
        updatebutton = findViewById(R.id.updateButton);

        nameTxtInputEditTxt = findViewById(R.id.nameUpdateText);
        emailTxtInputEditTxt = findViewById(R.id.emailUpdateText);

        createUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateUserActivity.class));
                finish();
            }
        });


        //GETTING SINGLE USER DATA
        Call<DataModel> call = networkCall.getData(1);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        DataModel modelclasses = response.body();

                        nameTextview.setText(modelclasses.getName());
                        emailTextview.setText(modelclasses.getEmail());

                    }

                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(Get_Delete_Update_DataActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        //DELETING USER DATA
        deleteuserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(id);

            }
        });


        //UPDATE DATA
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameTxtInputEditTxt.getText().toString().trim();
                String email = emailTxtInputEditTxt.getText().toString().trim();

                updateUser(name, email, id);

            }
        });
    }


    //DELETE USER FUNCTION
    private void deleteUser(int id) {
        Call<Retrofit_ResponseModel> callD = networkCall.deleteData(id);

        callD.enqueue(new Callback<Retrofit_ResponseModel>() {
            @Override
            public void onResponse(Call<Retrofit_ResponseModel> call, Response<Retrofit_ResponseModel> response) {
                if (response.isSuccessful()) {
                    Retrofit_ResponseModel model = response.body();
                    if (model.getName() == null) {
                        nameTextview.setText("DELETED");
                    }
                    if (model.getEmail() == null) {
                        emailTextview.setText("DELETED");
                    }
                    Toast.makeText(Get_Delete_Update_DataActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Retrofit_ResponseModel> call, Throwable t) {

            }
        });
    }


    //UPDATE FUNCTIONS
    private void updateUser(String name, String email, int id) {

        Retrofit_ResponseModel retrofitResponseModel = new Retrofit_ResponseModel(name, email);
        Call<Retrofit_ResponseModel> callup = networkCall.updateData(id, retrofitResponseModel);

        callup.enqueue(new Callback<Retrofit_ResponseModel>() {
            @Override
            public void onResponse(Call<Retrofit_ResponseModel> call, Response<Retrofit_ResponseModel> response) {
                if (response.isSuccessful()) {

                    Retrofit_ResponseModel model1 = response.body();

                    emailTextview.setText(model1.getEmail());
                    nameTextview.setText(model1.getName());
                    Toast.makeText(Get_Delete_Update_DataActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Retrofit_ResponseModel> call, Throwable t) {
                Toast.makeText(Get_Delete_Update_DataActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}