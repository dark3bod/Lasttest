package com.sultan.lasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogIn extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private ProgressBar progressBar;
    private Button  btnLogin, btnResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        // set the view now
        setContentView(R.layout.activity_log_in);



        inputEmail = (EditText) findViewById(R.id.userName);
        inputPassword = (EditText) findViewById(R.id.pass);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.login);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        /*FirebaseAuth auth = FirebaseAuth.getInstance();
         */

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, ResetPasswordActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            String email = inputEmail.getText().toString();
            final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }

                if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

                progressBar.setVisibility(View.VISIBLE);

            //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    progressBar.setVisibility(View.GONE);
                    if (!task.isSuccessful()) {
                        // there was an error
                        if (password.length() < 6) {
                            inputPassword.setError(getString(R.string.invalid_password));
                        } else {
                            Toast.makeText(LogIn.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Intent intent = new Intent(LogIn.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        });
    }
    ///disable back button while logged out
    @Override
    public void onBackPressed() {

    }
}
