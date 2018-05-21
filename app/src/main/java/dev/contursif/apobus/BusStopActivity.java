package dev.contursif.apobus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dev.contursif.apobus.model.BusStop;

public class BusStopActivity extends AppCompatActivity {

    private static final String TAG = BusStopActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop);
        final Intent srcIntent = getIntent();
        if (srcIntent != null){
            final String id = srcIntent.getStringExtra("id");
            final String name = srcIntent.getStringExtra("name");
            final String direction = srcIntent.getStringExtra("direction");
            final float latitude = srcIntent.getFloatExtra("latitude", 0.0f);
            final float longitude = srcIntent.getFloatExtra("longitude", 0.0f);
            final BusStop busStop = BusStop.Builder.create(id,name)
                    .withDirection(direction)
                    .withLocation(latitude,longitude)
                    .build();
            Log.d(TAG,"Received: "+ busStop);
        }
    }

}
