package com.example.lledent.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {
    private EditText loginInput;
    private EditText passwordInput;
    private Button sendButton;
    private Button signInButton;

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
