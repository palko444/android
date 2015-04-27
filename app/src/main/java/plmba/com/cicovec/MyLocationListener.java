package plmba.com.cicovec;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by pala on 4/27/15.
 */
public class MyLocationListener extends Activity implements LocationListener {


    LocationManager locationManager;
    Location location;
    TextView text;

    public MyLocationListener (Location location, TextView text) {
        this.location = location;
        this.text = text;
        onLocationChanged(location);
    }

    @Override
    public void onLocationChanged(Location location) {
        text.setText("Lat: " + location.getLatitude() + "\nLon: " + location.getLongitude());


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
