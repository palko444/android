package plmba.com.cicovec;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

/**
 * Created by pala on 4/26/15.
 */
public class ShowGPS extends Service implements LocationListener {

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_DISTANCE_BW__UPDATES = 1000 * 60 * 1;
    private final Context context;
    protected LocationManager locationManager;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    double latitude;
    double longitude;
    Location location;

    public ShowGPS(Context context) {
        this.context = context;
        getLocation();
    }

    private Location getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {

            } else {
                this.canGetLocation = true;

                if (isNetworkEnabled) {

                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_DISTANCE_BW__UPDATES, MIN_DISTANCE_BW__UPDATES, this);

                    if (locationManager != null) {

                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                        }

                    }
                }

                if (isGPSEnabled) {

                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_DISTANCE_BW__UPDATES, MIN_DISTANCE_BW__UPDATES, this);

                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(ShowGPS.this);
        }
    }

    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    public boolean canGetLocation() {
        return canGetLocation;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("Tittle");
        alertDialog.setMessage("GPS is not enabled. Switch to settings menu?");

        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);

            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
