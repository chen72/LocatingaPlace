package sg.edu.rp.c347.p08_locatingaplace;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnN, btnC, btnE;
    private GoogleMap map;

    Spinner spinner;

    private Marker hq,central,east;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnN =(Button)findViewById(R.id.btnN);
        btnC =(Button)findViewById(R.id.btnC);
        btnE =(Button)findViewById(R.id.btnE);
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //Your code for Item 1 select
                        LatLng poi_NorthHQ = new LatLng(1.461379, 103.815527);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_NorthHQ,
                                15));
                        break;
                    case 1:
                        LatLng poi_central = new LatLng(1.297662, 103.847486);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                                15));
                        //Your code for Item 2 select
                        break;
                    case 2:
                        LatLng poi_east = new LatLng(1.350000, 103.933830);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                                15));
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_NorthHQ = new LatLng(1.461379, 103.815527);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_NorthHQ,
                        15));
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_central = new LatLng(1.297662, 103.847486);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                        15));
            }
        });
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_east = new LatLng(1.350000, 103.933830);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                        15));
            }
        });


        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;


                LatLng poi_NorthHQ = new LatLng(1.461379, 103.815527);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_NorthHQ,
                        11));

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
                ui.setAllGesturesEnabled(true);

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getBaseContext(),"YOU CLICKED ON "+marker.getTitle()+marker.getSnippet(),Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
                 hq = map.addMarker(new
                        MarkerOptions()
                        .position(poi_NorthHQ)
                        .title("HQ-North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 ")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));


                LatLng poi_central = new LatLng(1.297662, 103.847486);
                 central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_east = new LatLng(1.350000, 103.933830);
                 east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")

                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));



                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }








            }
        });


    }






    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the read SMS
                    //  as if the btnRetrieve is clicked
                    int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION);
                    if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                    }

                } else {
                    // permission denied... notify user
                    Toast.makeText(MainActivity.this, "Permission not granted",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }





}
