package lohith.com.guardian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class launcherscreen extends AppCompatActivity {
    ImageView driver_n , patient_n;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcherscreen);
        driver_n = (ImageView) findViewById(R.id.driver);
        patient_n = (ImageView) findViewById(R.id.patient);

        driver_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(launcherscreen.this , Ambulance.class);
                startActivity(intent);
            }
        });

        patient_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(launcherscreen.this , MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
