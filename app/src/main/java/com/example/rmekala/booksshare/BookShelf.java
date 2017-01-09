package com.example.rmekala.booksshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookShelf extends AppCompatActivity {
    ImageButton addBookManually, addBookWithScan;
    EditText editText;
    Button btn;
    FirebaseDatabase database;
    DatabaseReference myRef,ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_shelf);
        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("weather");
        ref = database.getReference("Node");
        // Read from the database
/*        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
                Log.e("TAG", "Value is: " + dataSnapshot.getValue().toString());
                Toast.makeText(BookShelf.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("TAG", "Failed to read value."+ error.toException());
            }
        });*/

        initializeTheLayout();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        setupToolbar();
    }
    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    private void initializeTheLayout() {
        addBookManually = (ImageButton) findViewById(R.id.add_book_manually);
        addBookWithScan = (ImageButton) findViewById(R.id.add_book_with_scan);
        addBookManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookShelf.this, BookDetails.class);
                startActivity(intent);
            }
        });
        addBookWithScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        editText= (EditText) findViewById(R.id.text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn = (Button)findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().trim().length()>0) {
                    sendData(editText.getText().toString());
                }else{
                    editText.setError("Please Enter The Text");
                    Toast.makeText(BookShelf.this, "Please Enter The Text", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void sendData(String str){
//        ref.child("Name"+str).setValue(str);

        ref.child("Name"+str).child("test").setValue(str);

        editText.setText("");
//        myRef.setValue(str);
    }
}
