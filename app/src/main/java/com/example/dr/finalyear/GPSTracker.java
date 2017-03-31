package com.example.dr.finalyear;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by DR on 3/29/2017.
 */
public class GPSTracker extends Service implements LocationListener {
    private static String TAG = GPSTracker.class.getName();
    private final Context mcontext;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean isGPSTrackingEnabled = false;
    Location location;
    double latitude;
    double longitude;
    int geocoderMaxResults = 1;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;
    private static final long MIN_TIME_BW_UPDATES = 0;
    protected LocationManager locationManager;
    private String provider_info;

    public GPSTracker(Context context) {
        this.mcontext = context;
        getLocation();
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) mcontext.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (isGPSEnabled) {
                this.isGPSTrackingEnabled = true;
                Log.d(TAG, "Application use GPS Service");
                provider_info = LocationManager.GPS_PROVIDER;
            } else if (isNetworkEnabled) {
                this.isGPSTrackingEnabled = true;
                Log.d(TAG, "Application use Network state to get GPS Cordinates");
                provider_info = LocationManager.NETWORK_PROVIDER;
            }
            if (!provider_info.isEmpty()) {

                if (ActivityCompat.checkSelfPermission((Activity) mcontext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mcontext, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, 10);}
                locationManager.requestLocationUpdates(
                        provider_info,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES,
                        this
                );
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(provider_info);
                    updateGPSCoordinates();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Impossible to connect to LocationManager", e);
        }
    }

    public void updateGPSCoordinates() {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
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

    public boolean getIsGPSTrackingEnabled() {
        return this.isGPSTrackingEnabled;
    }

    public void stopUsingGPS() {
        if (ActivityCompat.checkSelfPermission((Activity) mcontext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mcontext, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 10);
            locationManager.removeUpdates(GPSTracker.this);
        }
    }
    public void showSettingsAlert()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mcontext);
        alertDialog.setTitle(R.string.GPSAlertDialogTitle);
        alertDialog.setMessage(R.string.GPSAlertDialogMessage);
        alertDialog.setPositiveButton(R.string.action_settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mcontext.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
    public List<Address> getGeocoderAddress(Context context)
    {
        if(location!=null)
        {
            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
            try
            {
                List<Address> addresses = geocoder.getFromLocation(latitude,longitude,this.geocoderMaxResults);
                return addresses;
            }
            catch (IOException e)
            {
                Log.e(TAG,"Impossible to connect to Geocoder",e);
            }
        }
        return null;
    }
    public String getAddressLine(Context context)
    {
        List<Address> addresses = getGeocoderAddress(context);
        if(addresses!=null && addresses.size()>0)
        {
            Address address = addresses.get(0);
            String addressLine = address.getAddressLine(0);
            return addressLine;
        }
        else {
            return null;
        }
    }
    public String getLocality(Context context)
    {
        List<Address> addresses = getGeocoderAddress(context);
        if(addresses!=null && addresses.size()>0)
        {
            Address address = addresses.get(0);
            String locality = address.getLocality();
            return locality;
        }
        else {
            return null;
        }
    }
    public String getPostalCode(Context context)
    {
        List<Address> addresses = getGeocoderAddress(context);
        if(addresses!=null && addresses.size()>0)
        {
            Address address = addresses.get(0);
            String postalCode = address.getPostalCode();
            return postalCode;
        }
        else {
            return null;
        }
    }
    public String getCountryName(Context context)
    {
        List<Address> addresses = getGeocoderAddress(context);
        if(addresses!=null && addresses.size()>0)
        {
            Address address = addresses.get(0);
            String countryName = address.getCountryName();
            return countryName;
        }
        else {
            return null;
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        updateGPSCoordinates();

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
