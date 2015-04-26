package plmba.com.cicovec;

import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {


    TextView text;
    Button btnShowLocation;
    ShowGPS gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.show_location);
        text = (TextView) findViewById(R.id.text);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps = new ShowGPS(MainActivity.this);

                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    //Toast.makeText(getApplicationContext(), "Your location is -\nLat: " + latitude + "\nLon: " + longitude, Toast.LENGTH_LONG).show();
                    text.setText("Cicova location  -\nLat :" + latitude + "\nLon :" + longitude);

                } else {
                    gps.showSettingsAlert();
                }

            }
        });

    }


}
