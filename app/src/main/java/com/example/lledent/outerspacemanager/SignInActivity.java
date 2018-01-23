package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
    private EditText loginInput;
    private EditText passwordInput;
    private Button sendButton;
    private Button signInButton;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);

        loginInput = (EditText) findViewById(R.id.signinLoginInputID);
        passwordInput = (EditText) findViewById(R.id.signinPasswordInputID);
        sendButton = (Button) findViewById(R.id.signinSendButtonID);
        signInButton = (Button) findViewById(R.id.signinSignInButtonID);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(loginInput.getText().toString());
                user.setPassword((passwordInput.getText().toString()));

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
                Call<ConnectionResponse> request= service.connectUser(user);

                request.enqueue(new Callback<ConnectionResponse>(){
                    @Override
                    public void onResponse(Call<ConnectionResponse> call, Response<ConnectionResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(),"Bienvenue !",Toast.LENGTH_SHORT).show();
                            token = response.body().token;

                            Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(),"Impossible de se connecter à ce compte",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ConnectionResponse> call, Throwable t) {


                    }
                });

            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signInActivity);
            }
        });
    }
}
