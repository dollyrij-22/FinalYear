package com.example.dr.finalyear;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MapsActivity extends FragmentActivity  {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mMap = mapFragment.getMap();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        String data;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://attendance-dr22libraryapp.rhcloud.com/upload.php");
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            data = EntityUtils.toString(entity);
            Log.e("STRING", data);
            try {

                JSONArray json = new JSONArray(data);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    String lati = obj.getString("latitude");
                    String longi = obj.getString("longitude");
                    /*lat.add(lati);
                    log.add(longi);*/

                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.valueOf(lati), Double.valueOf(longi)))
                            .title(Double.valueOf(lati).toString() + "," + Double.valueOf(longi).toString()));

                    Log.e("locations", Double.valueOf(lati).toString());
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        }
    }
}
