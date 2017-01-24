package com.desarrollador.easytour;

import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cercanos extends AppCompatActivity {

    private  Spinner spinner;
    private TextView titulo;
    SimpleCursorAdapter cursorAdapter;
    UbicacionManager ubicacionManager;
    UbicacionDBHelper ubicacionDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spinner=(Spinner) findViewById(R.id.spinner);
        titulo=(TextView)findViewById(R.id.titulo);
        setContentView(R.layout.activity_cercanos);
        ubicacionManager=new UbicacionManager(this);
        ubicacionDBHelper= new UbicacionDBHelper(this);
        ubicacionManager.open();
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


}


