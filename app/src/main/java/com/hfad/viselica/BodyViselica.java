package com.hfad.viselica;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BodyViselica extends AppCompatActivity {
    private View name;

     public final BodyViselica[] bodyPars = {
            new BodyViselica(findViewById(R.id.head)),
            new BodyViselica(findViewById(R.id.body)),
            new BodyViselica(findViewById(R.id.arm1)),
            new BodyViselica(findViewById(R.id.arm2)),
            new BodyViselica(findViewById(R.id.leg1)),
            new BodyViselica(findViewById(R.id.leg2))
    };

    private BodyViselica(View name) {
        this.name = name;
    }
}
