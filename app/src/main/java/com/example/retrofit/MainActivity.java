package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.retrofit.databinding.ActivityMainBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject=new JSONObject();

                try {
                    jsonObject.put("name",binding.name.getText().toString().trim());
                    jsonObject.put("mobile",binding.mobile.getText().toString().trim());
                    jsonObject.put("email",binding.email.getText().toString().trim());
                    jsonObject.put("pswrd",binding.pass.getText().toString().trim());
                    jsonObject.put("baction","register_user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://androindian.com/apps/example_app/")
                        .addConverterFactory(GsonConverterFactory.create()).build();

                RegInterface regInterface=retrofit.create(RegInterface.class);

                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                Log.i("object",object.toString());

                Call<RegResponse> regResponseCall=regInterface.createUser(object);

                regResponseCall.enqueue(new Callback<RegResponse>() {
                    @Override
                    public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {

                        String res = response.body().getResponse();

                        if (res.equalsIgnoreCase("success")){
                            Toast.makeText(MainActivity.this,""+response.body().getUser(),Toast.LENGTH_SHORT).show();
                        }
                        else  if (res.equalsIgnoreCase("failure")){
                            Toast.makeText(MainActivity.this,""+response.body().getUser(),Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,""+response.body().getUser(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this,""+t.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
