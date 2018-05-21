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
           /* final String id = srcIntent.getStringExtra("id");
            final String name = srcIntent.getStringExtra("name");
            final String direction = srcIntent.getStringExtra("direction");
            final float latitude = srcIntent.getFloatExtra("latitude", 0.0f);
            final float longitude = srcIntent.getFloatExtra("longitude", 0.0f);
            */
           /* final String id = srcIntent.getStringExtra(BusStop.Keys.ID);
            final String name = srcIntent.getStringExtra(BusStop.Keys.NAME);
            final String direction = srcIntent.getStringExtra(BusStop.Keys.DIRECTION);
            final float latitude = srcIntent.getFloatExtra(BusStop.Keys.LATITUDE, 0.0f);
            final float longitude = srcIntent.getFloatExtra(BusStop.Keys.LONGITUDE, 0.0f);
            final BusStop busStop = BusStop.Builder.create(id,name)
                    .withDirection(direction)
                    .withLocation(latitude,longitude)
                    .build();*/
            /*
            final BusStop busStop = (BusStop) srcIntent.getSerializableExtra(BusStop.Keys.ID);
            */
            final BusStop busStop = (BusStop) srcIntent.getParcelableExtra(BusStop.Keys.ID);
            Log.d(TAG,"Received: "+ busStop);
        }
    }

}
