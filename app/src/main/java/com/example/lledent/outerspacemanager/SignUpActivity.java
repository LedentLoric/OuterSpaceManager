package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lledent on 16/01/2018.
 */

public class SignUpActivity extends AppCompatActivity {
    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    private EditText loginInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button sendButton;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        loginInput = (EditText) findViewById(R.id.signupLoginInputID);
        emailInput = (EditText) findViewById(R.id.signupEmailInputID);
        passwordInput = (EditText) findViewById(R.id.signupPasswordInputID);
        sendButton = (Button) findViewById(R.id.signupSendButtonID);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(loginInput.getText().toString());
                user.setEmail(emailInput.getText().toString());
                user.setPassword((passwordInput.getText().toString()));

                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);
                Call<ConnectionResponse> request= service.createUser(user);

                request.enqueue(new Callback<ConnectionResponse> (){
                    @Override
                    public void onResponse(Call<ConnectionResponse> call, Response<ConnectionResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(),"Votre compte a bien été créé. Bienvenue !",Toast.LENGTH_SHORT).show();

                            token = response.body().token;

                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("token", token);
                            editor.commit();

                            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainActivity);

                        } else {
                            Toast.makeText(getApplicationContext(),"Impossible de créer le compte",Toast.LENGTH_SHORT).show();
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
