package com.example.adm.mobquestnew;

/**
 * Created by ADM on 18.10.2016.
 */

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by ADM on 18.10.2016.
 */
public class MapFragmentsClass extends Fragment {

    View v;
    MapView mMapView;
    private GoogleMap googleMap;
    MainActivity ActivMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.create_map, container, false);
        //Bundle bundle = getArguments();
        // Gets the MapView from the XML layout and creates it
        ActivMain = ((MainActivity) getActivity());
        ActivMain.getSupportActionBar().setTitle(R.string.toolbar_map);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(67.557996, 33.422129);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                /*googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {

                        Toast.makeText(getActivity().getBaseContext(), "v-"+marker.getId(), Toast.LENGTH_LONG).show();

                    }
                });*/
                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    //Use default infoWindow frame
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        // Getting view from the layout file
                        View view = ActivMain.getLayoutInflater().inflate(R.layout.marker_map, null);
                        // Getting the position from the marker
                        LatLng latLng = marker.getPosition();
                        //UI elements
                        int id_quest =  Integer.parseInt(marker.getTitle());

                        TextView title = (TextView) view.findViewById(R.id.marker_title);
                        TextView body = (TextView) view.findViewById(R.id.marker_body);
                        EditText ask = (EditText) view.findViewById(R.id.marker_ask);
                        Button enter = (Button) view.findViewById(R.id.marker_enter);

                        title.setText(ActivMain.MarkerDB.get(id_quest).getTitle());

                        if (ActivMain.MarkerDB.get(id_quest).getType() == 0) {
                            ask.setCursorVisible(false);
                            body.setText("Находиться на дистанции 5 метров от точки");
                            enter.setText("Проверка");
                            enter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        } else if (ActivMain.MarkerDB.get(id_quest).getType() == 1) {
                            body.setText("Отсканируйте QR-code");
                            enter.setText("Сканировать");
                            enter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        } else {
                            body.setText(ActivMain.MarkerDB.get(id_quest).getBody());
                            enter.setText("Проверить");
                            enter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }

                        return view;
                    }
                });
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
