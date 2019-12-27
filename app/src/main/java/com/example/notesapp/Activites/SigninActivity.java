package com.example.notesapp.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SigninActivity extends AppCompatActivity {

    private EditText logEmail, logPassword;
    private Button logLogin;
    private ProgressBar progressBar;
    TextView forget;


    //vars
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        forget = findViewById(R.id.forget);
        logEmail = findViewById(R.id.login_email);
        logPassword = findViewById(R.id.login_password);
        logLogin = findViewById(R.id.login_btn);

        progressBar = findViewById(R.id.register_pb);

        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this,ForgetpasswordActivity.class);
                startActivity(intent);
            }
        });

        logLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                logLogin.setVisibility(View.INVISIBLE);


                final String Email = logEmail.getText().toString();
                final String Password = logPassword.getText().toString();


                if (Email.length() == 0 || Password.length() == 0) {
                    showMessage("please fill the fields");
                    logLogin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    loginUser(Email, Password);
                    Intent intent = new Intent(SigninActivity.this,HomeActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    showMessage("Authentication Failed");
                    logLogin.setVisibility(View.VISIBLE);
                } else {
                    Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // redirect user to his home page
            startActivity(new Intent(SigninActivity.this, HomeActivity.class));
            finish();
        }
    }
}
