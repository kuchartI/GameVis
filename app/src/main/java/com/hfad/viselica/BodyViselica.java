package com.hfad.viselica;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class BodyViselica extends AppCompatActivity {
     public final ImageView[] bodyPars =new ImageView[] {
            findViewById(R.id.head),
            findViewById(R.id.body),
            findViewById(R.id.arm1),
            findViewById(R.id.arm2),
            findViewById(R.id.leg1),
            findViewById(R.id.leg2)
    };

}
