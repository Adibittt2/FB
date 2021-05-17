package com.aditya_verma.foodies_business;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Switch extends AppCompatActivity {

    public static String  on_off,on_off1,documents;

    SwitchCompat aaloo_tikki,burger,paneer_burger, lassi,frappuccino,pizza,french_fries,
                 chowmein,paneer_chilli_half,paneer_chilli_full;

    FirebaseFirestore Switch_db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        Switch_db = FirebaseFirestore.getInstance();


        switch_method();
        switch_method_aaloo_tikki();
        switch_method_burger();
        switch_method_paneer_burger();
        switch_method_chowmein();
        switch_method_chocolate_frappuccino();
        switch_method_lassi();
        switch_method_french_fries();
        switch_method_pizza();
        switch_method_paneer_chilli_half();
        switch_method_paneer_chilli_full();


    }

    public  void switch_method(){

        final SwitchCompat switch_btn = (SwitchCompat) findViewById(R.id.switch1);

      final   SharedPreferences sharedPreferences1 = getSharedPreferences("save1",MODE_PRIVATE);
        switch_btn.setChecked(sharedPreferences1.getBoolean("value",true));

        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_btn.isChecked()){
                    SharedPreferences.Editor editor1 = getSharedPreferences("save1", MODE_PRIVATE).edit();
                    editor1.putBoolean("value",true);
                    editor1.apply();
                    switch_btn.setChecked(true);
                    on_off1 = "ON";
                }
                else {
                    SharedPreferences.Editor editor1 = getSharedPreferences("save1", MODE_PRIVATE).edit();
                    editor1.putBoolean("value", false);

                    editor1.apply();
                    switch_btn.setChecked(false);
                    on_off1 = "OFF";
                }

                on_off();
            }
        });
    }

    public void on_off(){
        final String str1 = on_off1;
//        Map<String,String> userMap = new HashMap<>();
//        userMap.put("Shop",str);

        Switch_db.collection("Shop_On_Off").document(MainActivity.collection)
                .update("Shop",str1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // after the data addition is successful
                        // we are displaying a success toast message.
                        Toast.makeText(Switch.this,"Your Shop is "+str1, Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(Switch.this,"" + e , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void on_off_prducts(){
        final String str = on_off;
      //  Map<String,String> userMap = new HashMap<>();
      //  userMap.put("available",str);

        Switch_db.collection("Foodies").document(documents)
                .update("available",str)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // after the data addition is successful
                        // we are displaying a success toast message.
                        Toast.makeText(Switch.this, documents + " "+ str, Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(Switch.this,"" + e , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public  void switch_method_aaloo_tikki(){

        aaloo_tikki = (SwitchCompat) findViewById(R.id.switch_aaloo_tikki);


        SharedPreferences sharedPreferences2 = getSharedPreferences("save2",MODE_PRIVATE);
        aaloo_tikki.setChecked(sharedPreferences2.getBoolean("value",true));

        aaloo_tikki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aaloo_tikki.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save2", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    aaloo_tikki.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save2", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    aaloo_tikki.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Aaloo Tikki";

                on_off_prducts();
            }
        });


    }

    public  void switch_method_burger(){

        burger = (SwitchCompat) findViewById(R.id.switch_burger);


        SharedPreferences sharedPreferences3 = getSharedPreferences("save3",MODE_PRIVATE);
        burger.setChecked(sharedPreferences3.getBoolean("value",true));

        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(burger.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save3", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    burger.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save3", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    burger.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Burger";

                on_off_prducts();
            }
        });

    }


    public  void switch_method_paneer_burger(){

        paneer_burger = (SwitchCompat) findViewById(R.id.switch_paneer_burger);


        SharedPreferences sharedPreferences4 = getSharedPreferences("save4",MODE_PRIVATE);
        paneer_burger.setChecked(sharedPreferences4.getBoolean("value",true));

        paneer_burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paneer_burger.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save4", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    paneer_burger.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save4", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    paneer_burger.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Burger (Paneer)";

                on_off_prducts();
            }
        });

    }

    public  void switch_method_chowmein(){

        chowmein = (SwitchCompat) findViewById(R.id.switch_chowmein);


        SharedPreferences sharedPreferences5 = getSharedPreferences("save5",MODE_PRIVATE);
        chowmein.setChecked(sharedPreferences5.getBoolean("value",true));

        chowmein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chowmein.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save5", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    chowmein.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save5", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    chowmein.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Chowmein";

                on_off_prducts();
            }
        });

    }

    public  void switch_method_french_fries(){

        french_fries = (SwitchCompat) findViewById(R.id.switch_french_fries);


        SharedPreferences sharedPreferences6 = getSharedPreferences("save6",MODE_PRIVATE);
        french_fries.setChecked(sharedPreferences6.getBoolean("value",true));

        french_fries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(french_fries.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save6", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    french_fries.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save6", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    french_fries.setChecked(false);
                    on_off = "OFF";
                }
                documents = "French Fries";

                on_off_prducts();
            }
        });

    }

    public  void switch_method_chocolate_frappuccino(){

        frappuccino = (SwitchCompat) findViewById(R.id.switch_Frappuccino);


        SharedPreferences sharedPreferences7 = getSharedPreferences("save7",MODE_PRIVATE);
        frappuccino.setChecked(sharedPreferences7.getBoolean("value",true));

        frappuccino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(frappuccino.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save7", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    frappuccino.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save7", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    frappuccino.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Chocolate Frappuccino";

                on_off_prducts();
            }
        });

    }

    public  void switch_method_lassi(){

        lassi = (SwitchCompat) findViewById(R.id.switch_Lassi);


        SharedPreferences sharedPreferences8 = getSharedPreferences("save8",MODE_PRIVATE);
        lassi.setChecked(sharedPreferences8.getBoolean("value",true));

        lassi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lassi.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save8", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    lassi.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save8", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    lassi.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Lassi";

                on_off_prducts();
            }
        });

    }

    public  void switch_method_pizza(){

        pizza = (SwitchCompat) findViewById(R.id.swich_pizza);


        SharedPreferences sharedPreferences9 = getSharedPreferences("save9",MODE_PRIVATE);
        pizza.setChecked(sharedPreferences9.getBoolean("value",true));

        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pizza.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save9", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    pizza.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save9", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    pizza.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Pizza";

                on_off_prducts();
            }
        });

    }

    public  void switch_method_paneer_chilli_half(){

        paneer_chilli_half = (SwitchCompat) findViewById(R.id.switch_paneer_chilli_half);


        SharedPreferences sharedPreferences10 = getSharedPreferences("save10",MODE_PRIVATE);
        paneer_chilli_half.setChecked(sharedPreferences10.getBoolean("value",true));

        paneer_chilli_half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paneer_chilli_half.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save10", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    paneer_chilli_half.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save10", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    paneer_chilli_half.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Paneer Chilli (Half)";

                on_off_prducts();
            }
        });

    }

    public  void switch_method_paneer_chilli_full(){

        paneer_chilli_full = (SwitchCompat) findViewById(R.id.switch_paneer_chilli_full);


        SharedPreferences sharedPreferences11 = getSharedPreferences("save11",MODE_PRIVATE);
        paneer_chilli_full.setChecked(sharedPreferences11.getBoolean("value",true));

        paneer_chilli_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paneer_chilli_full.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save11", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    paneer_chilli_full.setChecked(true);
                    on_off = "ON";
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save11", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);

                    editor.apply();
                    paneer_chilli_full.setChecked(false);
                    on_off = "OFF";
                }
                documents = "Paneer Chilli (Full)";

                on_off_prducts();
            }
        });

    }


}