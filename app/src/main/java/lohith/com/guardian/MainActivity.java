 package lohith.com.guardian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import lohith.com.guardian.essentials.*;

import java.util.HashMap;

 public class MainActivity extends AppCompatActivity {
    TextView Heartbeat , temperature , BloodPressure;
    DatabaseReference databaseReference;
    double[] heartbeat , names;
    double[] temp;
    double[] BP;
    String my_name = "Vignesh";
    double lat , lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Heartbeat = (TextView) findViewById(R.id.heartcount);
        temperature = (TextView) findViewById(R.id.Temperaturecount);
        BloodPressure = findViewById(R.id.bloodp);
        databaseReference = FirebaseDatabase.getInstance().getReference("");
        essentials ess = new essentials();
        lat = ess.lat;
        lng = ess.lng;

    }

     @Override
     protected void onStart() {
         super.onStart();
         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 heartbeat = new double[(int)dataSnapshot.getChildrenCount()];
                 temp = new double[(int)dataSnapshot.getChildrenCount()];
                 BP = new double[(int)dataSnapshot.getChildrenCount()];
                 int i = 0;
                 Log.e(String.valueOf(dataSnapshot), "onDataChange: -=-==-=-" );
                 for( DataSnapshot def : dataSnapshot.getChildren() )
                 {
                     Log.e(String.valueOf(def), "onDataChange: =-=-=-=");
                     if( i == 0 )
                     {
                         BP[0] = (double) def.getValue();
                     }
                     else if( i == 1 )
                     {
                         heartbeat[0] = (double) def.getValue();
                     }
                     else if( i == 2 )
                     {
                         temp[0] = (double) def.getValue();
                     }
                     i++;

                 }
                Heartbeat.setText(String.valueOf(heartbeat[0]));
                 temperature.setText(String.valueOf(temp[0]));
                 BloodPressure.setText(String.valueOf(BP[0]));

                 if( (heartbeat[0] < 50 || heartbeat[0] >120)  || (temp[0] < 30 || temp[0] > 50)  )
                 {

                     locationdetails location = new locationdetails(lat,lng);
                     databaseReference.child("emergency1").setValue(location);

                 }
             }




             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
     }
 }
