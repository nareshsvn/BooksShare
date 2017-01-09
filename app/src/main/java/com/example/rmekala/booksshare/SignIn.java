package com.example.rmekala.booksshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {
EditText email,passWord,reEnterPassword;
Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setupToolbar();
        initializeLayout();
    }
    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void initializeLayout(){
        email =(EditText) findViewById(R.id.email);
        passWord =(EditText) findViewById(R.id.password);
        reEnterPassword =(EditText) findViewById(R.id.reenter_password);
        signIn = (Button) findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SignIN stuff Here
                if(validateForm()){

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
    private boolean validateForm(){
        boolean returnValue = true;
        if(email.getText().toString().trim().length()<=0){
            email.setError("Enter Email...");
            returnValue = false;
        }
        if(passWord.getText().toString().trim().length()<=0){
            passWord.setError("Enter Password...");
            returnValue = false;
        }
        if(reEnterPassword.getText().toString().trim().length()<=0){
            reEnterPassword.setError("Enter Re EnterPassword...");
            returnValue = false;
        }
        if(!reEnterPassword.getText().toString().equals(passWord.getText().toString())){
            passWord.setError("Password didn't match ...");
            reEnterPassword.setError("Password didn't match ...");
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }
}
