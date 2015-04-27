package plmba.com.cicovec;

import android.app.Activity;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {


    TextView text;
    TextView text_oo_nw;
    TextView text_oo_gps;
    Button btnShowLocation;
    Button on_off;
    Button on_off_gps;
    ShowGPS gps;
    LocationListener locationListener;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.on_off_ot);
        on_off_gps = (Button) findViewById(R.id.on_off_gps);
        on_off = (Button) findViewById(R.id.on_off_nw);
        text = (TextView) findViewById(R.id.text);
        text_oo_nw = (TextView) findViewById(R.id.text_permanent_nw);
        text_oo_gps = (TextView) findViewById(R.id.text_permanent_gps);

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

        on_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {
                gps = new ShowGPS(MainActivity.this);

                if (gps.canGetLocation()) {
                    LocationListener locationListener = new MyLocationListener(gps.location, text_oo_nw);
                    locationManager = gps.locationManager;
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

        on_off_gps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {
                gps = new ShowGPS(MainActivity.this);

                if (gps.canGetLocation()) {
                    LocationListener locationListener = new MyLocationListener(gps.location,text_oo_gps);
                    locationManager = gps.locationManager;
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                } else {
                    gps.showSettingsAlert();
                }
            }
        });


    }

}
