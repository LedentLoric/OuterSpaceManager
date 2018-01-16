package com.example.lledent.outerspacemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lledent on 16/01/2018.
 */

public class SignInActivity extends Activity {
    private EditText loginInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        loginInput = (EditText) findViewById(R.id.signinLoginInputID);
        emailInput = (EditText) findViewById(R.id.signinEmailInputID);
        passwordInput = (EditText) findViewById(R.id.signinPasswordInputID);
        sendButton = (Button) findViewById(R.id.signinSendButtonID);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(loginInput.getText().toString());
                user.setEmail(emailInput.getText().toString());
                user.setPassword((passwordInput.getText().toString()));

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
                Call<ConnectionResponse> request= service.createUser(user);

                request.enqueue(new Callback<ConnectionResponse> (){
                    @Override
                    public void onResponse(Call<ConnectionResponse> call, Response<ConnectionResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(),"Le compte a été créé.",Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(),"Erreur",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ConnectionResponse> call, Throwable t) {

                        
                    }
                });
            }
        });
    }
}
