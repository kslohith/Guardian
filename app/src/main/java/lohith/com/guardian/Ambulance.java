package lohith.com.guardian;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ambulance extends AppCompatActivity {
    DatabaseReference databaseReference , databaseReference1 , databaseReference2;
    double latitude;
    double longitude;
    TextView helpmep;
    double Temp , heart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpme);
        helpmep = findViewById(R.id.helpme1);
        databaseReference = FirebaseDatabase.getInstance().getReference("emergency");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("HeartBeat");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Temperature");

                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Temp = (double) dataSnapshot.getValue();
                        Log.e(String.valueOf(Temp), "onDataChange: temp");
                        databaseReference2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                heart = (double) dataSnapshot.getValue();
                                Log.e(String.valueOf(heart), "onDataChange: ---------------===============" );
                                Log.e(String.valueOf(Temp), "onDataChange: 949837408924702" );

                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Log.e( heart + " " + Temp, "onDataChange: 090909" );
                                        if( (Temp < 50 || Temp > 120)  || (heart < 30 || heart > 50)  ) {
                                            helpmep.setVisibility(View.VISIBLE);
                                        }
                                        locationdetails location = dataSnapshot.getValue(locationdetails.class);
                                        latitude = location.getLat();
                                        longitude = location.getLng();
                                        Log.e( heart + " " + Temp, "onDataChange: heart----------=" );

                                        helpmep.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude;
                                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                                                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                                startActivity(intent);
                                            }
                                        });

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });







            }
    }

