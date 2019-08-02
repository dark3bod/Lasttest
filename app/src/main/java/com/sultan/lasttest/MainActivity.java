package com.sultan.lasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = FirebaseAuth.getInstance().getCurrentUser();
        checkCurrentUser(user);
    }
    private void checkCurrentUser(FirebaseUser user) {
        // [START check_current_user]

        if (user != null) {
            //splash screen is over go to main menu





        } else {
            // No user is signed in
            //Login d = new login(user);
            //            //d.loginmeth()
            Toast.makeText(getApplicationContext(),"logged out",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }
        // [END check_current_user]
    }


}
