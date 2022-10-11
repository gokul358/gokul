package com.example.examplessss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in_page extends AppCompatActivity {
    private EditText name, email, password , confirm_password;
    private Button sign_in;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        sign_in = findViewById(R.id.btnregister);
        progressbar = findViewById(R.id.progressbar);

        // Set on Click Listener on Registration button
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

        private void registerNewUser() {

            // show the visibility of progress bar to show loading
            progressbar.setVisibility(View.VISIBLE);

            // Take the value of two edit texts in Strings
            String username, mail, pwd , cfm_pass;
            username = name.getText().toString();
            mail = email.getText().toString();
            pwd = password.getText().toString();
            cfm_pass = confirm_password.getText().toString();

            // Validations for input email and password
            if (username.isEmpty()) {
                Toast.makeText(getApplicationContext(),
                                "Please enter Name!!",
                                Toast.LENGTH_LONG)
                        .show();
                return;
            }
            if (mail.isEmpty()) {
                Toast.makeText(getApplicationContext(),
                                "Please enter E-mail!!",
                                Toast.LENGTH_LONG)
                        .show();
                return;
            }
            if (pwd.isEmpty()) {
                Toast.makeText(getApplicationContext(),
                                "Please enter password!!",
                                Toast.LENGTH_LONG)
                        .show();
                return;
            }if (cfm_pass.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            "Please enter confirm password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

            // create new user or register new user
            mAuth
                    .createUserWithEmailAndPassword(mail, pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                                "Registration successful!",
                                                Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
                                progressbar.setVisibility(View.GONE);

                                // if the user created intent to login activity
                                Intent intent = new Intent(sign_in_page.this, login_page.class);
                                startActivity(intent);
                            }
                            else {

                                // Registration failed
                                Toast.makeText(
                                                getApplicationContext(),
                                                "Registration failed!!"
                                                        + " Please try again later",
                                                Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
                                progressbar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
}