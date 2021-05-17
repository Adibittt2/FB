package com.aditya_verma.foodies_business;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Login extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteRef = db.collection("User_Password").document("User_Password");

    Database database;
    EditText user_id, password;
    Button log_in;
    String S_Raj_id,S_Raj_password,Cakery_id,Cakery_password,Foodies_id,Foodies_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = new Database(this);

        log_in = findViewById(R.id.login);
        user_id = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
       // checkbox = (CheckBox)findViewById(R.id.checkbox);

        getData();
        fetching_method();
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database.insertData(user_id.getText().toString(),password.getText().toString());

                if (user_id.getText().toString().equals(S_Raj_id) & password.getText().toString().equals(S_Raj_password)){

                    MainActivity.collection ="S_Raj_Order";
//                    Intent entry = new Intent(Login.this,MainActivity.class);
//                    startActivity(entry);
                }

                else if (user_id.getText().toString().equals(Cakery_id) & password.getText().toString().equals(Cakery_password)){

                    MainActivity.collection ="Cakery_Order";
//                    Intent entry = new Intent(Login.this,Cakery_Order.class);
//                    startActivity(entry);
                }

                else if (user_id.getText().toString().equals(Foodies_id) & password.getText().toString().equals(Foodies_password)){

                    MainActivity.collection ="Foodies_Order";
//                    Intent entry = new Intent(Login.this,Foodies_Order.class);
//                    startActivity(entry);
                }

                else{

                    user_id.setError("Wrong Id");
                    user_id.requestFocus();

                    password.setError("Wrong Password");
                    password.requestFocus();
                }
                Intent entry = new Intent(Login.this,MainActivity.class);
                startActivity(entry);

            }
        });
    }

    public void fetching_method(){
      noteRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override
        public void onSuccess (DocumentSnapshot documentSnapshot){
            if (documentSnapshot.exists()) {
                List<String> login = (List<String>) documentSnapshot.get("User_Password");
                 S_Raj_id = ("" + login.toArray()[0]);
                 S_Raj_password = ("" + login.toArray()[1]);
                 Cakery_id = ("" + login.toArray()[2]);
                 Cakery_password = ("" + login.toArray()[3]);
                 Foodies_id = ("" + login.toArray()[4]);
                 Foodies_password = ("" + login.toArray()[5]);

            } else {
                Toast.makeText(Login.this, "Document does not exist", Toast.LENGTH_SHORT).show();

            }
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure (@NonNull Exception e){

        }
    });

    }


    public void getData(){
                Cursor cursor = database.get_poora_Data();
        if(cursor.getCount()==0){
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();

        }

        else {
            while (cursor.moveToNext()) {
                user_id.setText(cursor.getString(0));
                password.setText(cursor.getString(1));
            }
            Toast.makeText(this, "data added", Toast.LENGTH_SHORT).show();

        }

    }
}