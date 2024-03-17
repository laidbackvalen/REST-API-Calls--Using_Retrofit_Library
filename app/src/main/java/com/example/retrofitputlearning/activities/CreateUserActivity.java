package com.example.retrofitputlearning.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitputlearning.R;
import com.example.retrofitputlearning.api.RetrofitAPI_NetworkCall;
import com.example.retrofitputlearning.models.Retrofit_ResponseModel;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateUserActivity extends AppCompatActivity {
    TextInputEditText nameinputEditxt, emailinputEditxt;
    TextView existingdataTextView, showDatatextview, deleteorupdateData;
    Button createbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        existingdataTextView = findViewById(R.id.viewDataUserTextView);
        nameinputEditxt = findViewById(R.id.nameTextInputEditText);
        emailinputEditxt = findViewById(R.id.emailTextInputEditText);
        createbutton = findViewById(R.id.createButton);
        showDatatextview = findViewById(R.id.showCreatedDatarTextView);
        deleteorupdateData = findViewById(R.id.deleteRrUpdateTextView);

        existingdataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GetAllDataActivity.class));
                finish();
            }
        });

        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(nameinputEditxt.getText().toString().trim()) || TextUtils.isEmpty(emailinputEditxt.getText().toString().trim())) {
                    emailinputEditxt.setError("Make sure all field are not left Empty");
                    return;
                }
                //Create Function Call
                createUser();
            }
        });

        deleteorupdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Get_Delete_Update_DataActivity.class));
            }
        });
    }  //ONCREATE ENDS HERE


    //CREATE USER FUNCTION USING RETROFIT
    private void createUser() {

        Retrofit retrofit = new Retrofit.Builder() // creates a new instance of the Retrofit builder.
                .baseUrl(RetrofitAPI_NetworkCall.BASE_URL)
                //.addConverterFactory - creates a Gson converter factory instance.
                .addConverterFactory(GsonConverterFactory.create())  //is where you specify that Gson will be used to convert JSON data to Java objects and vice versa.
                //GsonConverterFactory.create() - creates a Gson converter factory instance.
                //This converter factory takes care of converting JSON response bodies into Java objects and vice versa
                .build(); // builds the Retrofit instance with the configured settings.

        //Once you have the Retrofit instance, you can use it to create service interfaces (RetrofitAPI_NetworkCall)
        //that define your API endpoints and make network requests using these interfaces.


        RetrofitAPI_NetworkCall networkCall = retrofit.create(RetrofitAPI_NetworkCall.class);  // retrofit.create() here is a method provided by Retrofit
                                                                                              // that creates an implementation of the specified interface.

       //networkCall is the object created by Retrofit, which will be used to make network calls to the defined endpoints.
      //For example, if RetrofitAPI_NetworkCall contains a method getData(), you would call it

        Retrofit_ResponseModel retrofitResponseModel = new Retrofit_ResponseModel(nameinputEditxt.getText().toString().trim(), emailinputEditxt.getText().toString().trim());

        Call<Retrofit_ResponseModel> call = networkCall.createData(retrofitResponseModel);  //This method is likely used to make a network request to create some data on the server.


        //call.enqueue() is a method that executes the network call asynchronously, meaning it doesn't block the main thread.
        call.enqueue(new Callback<Retrofit_ResponseModel>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<Retrofit_ResponseModel> call, Response<Retrofit_ResponseModel> response) {
                if (response.isSuccessful()) {

                    Retrofit_ResponseModel model = response.body();
                    showDatatextview.setText("RESPONSE CODE : " + response.code() + "\n\n" + "NAME : " + model.getName() + "\n\n" + "Email : " + model.getEmail());
//                            Log.d("createUserTAG", "onResponse: SuccessfullyPosted " + response.code());
//                            Toast.makeText(CreateUserActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Retrofit_ResponseModel> call, Throwable t) {
                Toast.makeText(CreateUserActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}