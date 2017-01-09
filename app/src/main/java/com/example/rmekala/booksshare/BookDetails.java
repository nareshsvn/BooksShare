package com.example.rmekala.booksshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rmekala on 6/17/2016.
 */
public class BookDetails extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef,ref,ref_category,ref_language,ref_tag;
    EditText book_name,tag_name;
    Button bd_submit_button;
    Spinner spinner,spinner_language;
    String selected_category,selected_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.book_details);


        book_name = (EditText) findViewById(R.id.tv_bookname);
        tag_name = (EditText) findViewById(R.id.tv_tag);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.basic_categories_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner_language = (Spinner) findViewById(R.id.spinner_language);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.language_list_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner_language.setAdapter(adapter1);

        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("weather");
        ref = database.getReference("BookShare");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_category=spinner.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(),selected_category,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_language=spinner_language.getSelectedItem().toString();
                //selected_category=spinner_language.

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bd_submit_button = (Button) findViewById(R.id.bd_submit_button);
        bd_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(book_name.getText().toString().trim().length()>0) {

                    sendData(ref,selected_language,selected_category,book_name.getText().toString(),tag_name.getText().toString());
                    //sendData(ref,"TELUGU","Children's","Chinnari Chitti","Village");

                }else{
                    book_name.setError("Please Enter The Text");
                    Toast.makeText(BookDetails.this, "Please Enter The Text", Toast.LENGTH_SHORT).show();

                }
            }
        });
        // Read from the database
/*        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
                Log.e("TAG", "Value is: " + dataSnapshot.getValue().toString());
                Toast.makeText(BookDetails.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("TAG", "Failed to read value."+ error.toException());
            }
        });*/

    }


    @Override
    public void onBackPressed() {
        finish();
    }

/*    private void sendData(String str){
//        ref.child("Name"+str).setValue(str);

        ref.child("BOOK_NAME"+str).setValue(str);
        //ref.child("Name"+str).child("test").setValue(str);
        book_name.setText("");
//        myRef.setValue(str);
    }*/

    private void sendData(DatabaseReference dbr,String language,String category,String bookname, String tag_name){
//        ref.child("Name"+str).setValue(str);

        dbr.child(language).child(category).child(bookname).setValue(tag_name);
        //ref.child("Name"+str).child("test").setValue(str);
        book_name.setText("");
//        myRef.setValue(str);
    }

}
