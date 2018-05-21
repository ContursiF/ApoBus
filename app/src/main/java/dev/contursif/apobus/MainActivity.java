package dev.contursif.apobus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import dev.contursif.apobus.model.BusStop;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_BUS_STOP_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);*/
     /*   final Button sendBusStopButton = (Button) findViewById(R.id.send_bus_stop_button);
        sendBusStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBusStopData(v);
            }
        });*/
    }
    /*
    public void sendBusStopData(final View view){
        final BusStop busStop = BusStop.Builder.create("myId","myName")
                .withDirection("myDirection")
                .withLocation(51.5085300f,-0.1257400f)
                .build();
        final Intent intent = new Intent(this, BusStopActivity.class);
        intent.putExtra("id", busStop.id);
        intent.putExtra("name",busStop.name);
        intent.putExtra("direction",busStop.direction);
        intent.putExtra("latitude",busStop.latitude);
        intent.putExtra("longitude",busStop.longitude);
        startActivity(intent);

    }*/
    public void sendBusStopData(final View view) {

        final BusStop busStop = BusStop.Builder.create("myId", "myName")
                .withDirection("myDirection")
                .withLocation(51.5085300f, -0.1257400f)
                .build();

        final Intent intent = new Intent(this, BusStopActivity.class);
       /*
        intent.putExtra("id", busStop.id);
        intent.putExtra("name", busStop.name);
        intent.putExtra("direction", busStop.direction);
        intent.putExtra("latitude", busStop.latitude);
        intent.putExtra("longitude", busStop.longitude);
  */
        //intent.putExtra(BusStop.Keys.ID, busStop);
        busStop.toIntent(intent);
        startActivity(intent);
    }

    public void pickBusStop(final View view) {
        final Intent pickBusStopIntent = new Intent(BusStopListActivity.PICK_BUS_STOP_ACTION);
        pickBusStopIntent.putExtra(BusStop.Keys.LATITUDE, 51.5085300f);
        pickBusStopIntent.putExtra(BusStop.Keys.LONGITUDE, -0.1257400f);
        startActivityForResult(pickBusStopIntent, PICK_BUS_STOP_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_BUS_STOP_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    // We got the result
                    final BusStop busStop = data.getParcelableExtra(BusStop.Keys.ID);
                    Toast.makeText(this, "BusStop:" + busStop, Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    // Operation cancelled
                    Toast.makeText(this, R.string.cancelled_operation, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    // Other values
                    Toast.makeText(this, R.string.custom_operation, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
