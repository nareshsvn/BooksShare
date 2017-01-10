package com.example.rmekala.booksshare;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth; // Firebase Auth
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressDialog progressDialog;
    EditText email, passWord, reEnterPassword;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signUpSetup();
        setupToolbar();
        initializeLayout();
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void signUpSetup(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.e("mAuthListener", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.e("mAuthListener", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeLayout() {
        email = (EditText) findViewById(R.id.email);
        passWord = (EditText) findViewById(R.id.password);
        reEnterPassword = (EditText) findViewById(R.id.reenter_password);
        signIn = (Button) findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SignIN stuff Here
                if (validateForm()) {
                    progressDialog = new ProgressDialog(SignIn.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setTitle("Just a movement! we are creating an account for you");
                    progressDialog.show();
                    createNewUserAccount();
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passWord.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        reEnterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reEnterPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean validateForm() {
        boolean returnValue = true;
        if (email.getText().toString().trim().length() <= 0) {
            email.setError("Enter Email...");
            returnValue = false;
        }
        if (passWord.getText().toString().trim().length() <= 0) {
            passWord.setError("Enter Password...");
            returnValue = false;
        }
        if (reEnterPassword.getText().toString().trim().length() <= 0) {
            reEnterPassword.setError("Enter Re EnterPassword...");
            returnValue = false;
        }
        if (!reEnterPassword.getText().toString().equals(passWord.getText().toString())) {
            passWord.setError("Password didn't match ...");
            reEnterPassword.setError("Password didn't match ...");
            returnValue = false;
        }
        return returnValue;
    }
    private void createNewUserAccount(){
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), passWord.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        Log.e("SignIn Complete", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.e("SignIn Success","Success"+task.getResult().toString());
                            Toast.makeText(SignIn.this, "Account is created successfully.", Toast.LENGTH_SHORT).show();
                            email.setText("");
                            passWord.setText("");
                            reEnterPassword.setText("");
                            onBackPressed();
                        }else {
                            Log.e("SignIn Failure", "Failure :" + task.getResult().toString());
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }
}
