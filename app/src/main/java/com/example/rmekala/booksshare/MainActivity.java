package com.example.rmekala.booksshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeLayout();

//
//        try {
//            AssetDatabaseOpenHelper.openDatabase();
//        }catch (Exception e){
//            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//        }
//        try{
//
//
//
//        }catch (Exception e){
//            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//        }
    }

    public void onButtonClick(View v){
        if(v.getId()==R.id.login_button){

            Intent i = new Intent(MainActivity.this,MainOptions.class);
            startActivity(i);
        }




    }
    private void initializeLayout(){
        signIn = (Button) findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignIn.class);
                startActivity(intent);
            }
        });
    }
}
