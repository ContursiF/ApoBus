package dev.contursif.apobus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dev.contursif.apobus.model.BusStop;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);*/
        final Button sendBusStopButton = (Button) findViewById(R.id.send_bus_stop_button);
        sendBusStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBusStopData(v);
            }
        });
    }
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

    }
}
