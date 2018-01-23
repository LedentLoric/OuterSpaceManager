package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
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
    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private EditText loginInput;
    private EditText passwordInput;
    private Button sendButton;
    private Button signUpButton;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        token = settings.getString("token", "");

        if (token != "") {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
        }

        loginInput = (EditText) findViewById(R.id.signinLoginInputID);
        passwordInput = (EditText) findViewById(R.id.signinPasswordInputID);
        sendButton = (Button) findViewById(R.id.signinSendButtonID);
        signUpButton = (Button) findViewById(R.id.signinSignUpButtonID);

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

                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("token", token);
                            editor.commit();

                            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainActivity);

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
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signUpActivity);
            }
        });
    }
}
