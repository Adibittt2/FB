package com.aditya_verma.foodies_business;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FirebaseMessaging firebaseMessaging;

    FirebaseFirestore MainActivity_db;
    String token;

    private static final int REQUEST_CALL = 1;


    private FirestoreRecyclerAdapter<DataModal, ViewHolder> adapter;
    Database main_act_database;
    public static String collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_act_database = new Database(this);

        MainActivity_db = FirebaseFirestore.getInstance();

        new_method();

        token_sending_method();

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Switch.class);
                startActivity(intent);
            }
        });
    }

    public void token_sending_method(){
        // for sending notification to all
        firebaseMessaging.getInstance().subscribeToTopic("all");


        // fcm settings for perticular user

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            token = Objects.requireNonNull(task.getResult()).getToken();


                            //this code is only to send token_value to FIRESTORE.
                            // THIS IS  THE ONLY PLACE WHERE WE GET TOKEN VALUE OTHERWISE NULL


//                            Map<String,String> userMap1 = new HashMap<>();
//                            userMap1.put("Shop",token);


                            MainActivity_db.collection("Shop_token").document(collection)
                                    .update("Shop",token)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // after the data addition is successful
                                            // we are displaying a success toast message.
                                            Toast.makeText(MainActivity.this,token, Toast.LENGTH_SHORT).show();
                                        }

                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // this method is called when the data addition process is failed.
                                    // displaying a toast message when data addition is failed.
                                    Toast.makeText(MainActivity.this,"token sending failed" + e , Toast.LENGTH_SHORT).show();
                                }
                            });


                        }


                    }

                });

    }

    public void new_method(){

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();

        final Query query = rootRef.collection(collection)
                .orderBy("date", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<DataModal> options = new FirestoreRecyclerOptions.Builder<DataModal>()
                .setQuery(query, DataModal.class)
                .build();

       // main_act_database.addModel(query);

        adapter = new FirestoreRecyclerAdapter<DataModal, ViewHolder>(options) {

                @Override
                public void onDataChanged() {

                        startAlert();


                    // Called each time there is a new query snapshot. You may want to use this method
                    // to hide a loading spinner or check for the "no documents" state and update your UI.
                    // ...
                }

            @Override
            public void onError(FirebaseFirestoreException e) {
                // Called when there is an error getting a query snapshot. You may want to update
                // your UI to display an error message to the user.
                // ...

            }


            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull final DataModal model) {
                // setting data to our views in Recycler view items.
               // DataModal model = model.get(position);
                holder.name.setText("Name: " + model.getName());
             //   holder.mobile.setText("Mobile: "+ model.getMobile());
                holder.near_area.setText("Near_Area: "+ model.getNear_area());
                holder.address.setText("Address: "+ model.getAddress());
               // holder.location_text.setText(model.getLocation_text());
                holder.mode_of_payment.setText("Payment :"+ model.getMode_of_payment());
                holder.total_bill_price.setText("Total_Bill: "+ model.getTotal_bill_price());

                final List<String> array_list = model.getFood_list();
                holder.food_list.setText(""+ array_list);


                holder.time.setText(""+ model.getDate());



                holder.mobile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]
                                    {Manifest.permission.CALL_PHONE},REQUEST_CALL);
                        }
                        else {
                            Intent call = new Intent(Intent.ACTION_CALL);
                            call.setData(Uri.parse("tel:"+ model.getMobile()));
                            startActivity(call);
                        }
                    }
                });

                holder.location_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String location = model.getLocation_text();
                        Display_Track(location);
                    }
                });


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // setting on click listener
//                // for our items of recycler items.
//                Toast.makeText(context, "" + array_list , Toast.LENGTH_SHORT).show();
//            }
//        });

            }
        };

        recyclerView.setAdapter(adapter);
    }

    public void Display_Track(String location){

        try{
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + "" + "/" + location);

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
        catch(ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }




    private  class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our
        // views of recycler items.
        private TextView name, near_area, address, food_list, mode_of_payment, total_bill_price;

        private TextView time;

        private ImageView location_text,mobile;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing the views of recycler views.
            name = itemView.findViewById(R.id.name);
            mobile = itemView.findViewById(R.id.mobile);
            near_area = itemView.findViewById(R.id.near_area);
            address = itemView.findViewById(R.id.address);
            location_text = itemView.findViewById(R.id.location_text);
            mode_of_payment = itemView.findViewById(R.id.mode_of_payment);
            total_bill_price = itemView.findViewById(R.id.total_bill_price);
            food_list = itemView.findViewById(R.id.food_list);
            time = itemView.findViewById(R.id.time);
        }

    }

    public void startAlert() {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() +1000*5, pendingIntent);

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }

}
