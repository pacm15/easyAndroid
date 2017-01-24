package com.desarrollador.easytour;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.desarrollador.BD.UbicacionDBHelper;
import com.desarrollador.BD.UbicacionManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Lugares extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /*

     */

    TextView comida;
    TextView hotel;
    TextView gasolinera;

    UbicacionManager ubicacionManager;
    private Spinner spinner;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[]{UbicacionDBHelper.KEY_NAME};
    final int[] to = new int[]{R.id.titulo};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);
        comida=(TextView)findViewById(R.id.comida);
        hotel=(TextView)findViewById(R.id.hotel);
        gasolinera=(TextView)findViewById(R.id.gasolineras);
        ubicacionManager = new UbicacionManager(this);
        ubicacionManager.open();
        Cursor cursor = ubicacionManager.fetch();
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new SimpleCursorAdapter(this, R.layout.spinner_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);
        //spinner.setOnItemClickListener(this);


    }
    public void setLoc(Location loc) {
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address dirCalle = list.get(0);
                    String text = String.format("Direccion: " + dirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void hotLoc(Location loc) {
        if ( loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address dirCalle = list.get(0);
                    String text = String.format("Direccion: " + dirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int idspinner=parent.getId();
        Cursor c1=(Cursor) parent.getItemAtPosition(position);
        String titleSelection=c1.getString(c1.getColumnIndex(UbicacionDBHelper.KEY_LAT));
        activeSpinner(titleSelection);
    }

    private void activeSpinner(String titleSelection) {
        adapter=new SimpleCursorAdapter(
                this,android.R.layout.simple_spinner_item,
                ubicacionManager.getLati_Lon(titleSelection),
                new String[]{UbicacionDBHelper.KEY_NAME},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(this);
        //hotel.setText();
    }
}