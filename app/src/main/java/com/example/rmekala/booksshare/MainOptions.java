package com.example.rmekala.booksshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by rmekala on 6/17/2016.
 */
public class MainOptions extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_options);
    }

    public void onButtonClick(View v){

        if (v.getId()==R.id.button_donate) {

            Intent i = new Intent(MainOptions.this, donate_a_book.class);
            startActivity(i);
        }

        if (v.getId()==R.id.button_share){

                Intent i = new Intent(MainOptions.this,share_a_book.class);
                startActivity(i);
        }

        if(v.getId()==R.id.button_sell){
            Intent i = new Intent(MainOptions.this,sell_a_book.class);
            startActivity(i);
        }

        if(v.getId()==R.id.button_book_shelf){
            Intent intent = new Intent(MainOptions.this,BookShelf.class);
            startActivity(intent);
        }

    }
}
