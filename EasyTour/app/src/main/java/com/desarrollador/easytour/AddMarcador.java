package com.desarrollador.easytour;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.desarrollador.BD.UbicacionManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class AddMarcador extends AppCompatActivity {
    String dir;
    TextView lat, lon, calle, men;
    Button guardar, capturar;
    EditText nombre, descripcion;
    Location location;
    AlertDialog alert = null;
    private UbicacionManager ubicacionManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Añadir punto");
        setContentView(R.layout.activity_datos);
        /**
         * Referencias a los botones  del layout
         */
        lat = (TextView) findViewById(R.id.lat);
        lon = (TextView) findViewById(R.id.lon);
        men = (TextView) findViewById(R.id.mensaje);
        calle = (TextView) findViewById(R.id.calle);


        nombre = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.descrip);

        guardar = (Button) findViewById(R.id.guardar);
        capturar = (Button) findViewById(R.id.camara);

        ubicacionManager = new UbicacionManager(this);
        ubicacionManager.open();

        /**
         * Objetos para la base de datos
         /**
         * Para llamar el servicio de la camara
         */

        capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddMarcador.this,Camera.class);
                dir=i.getStringExtra(Camera.DIR_TAG);

                startActivity(i);

            }
        });

        /**
         * Servicio de localizacion
         */
        final LocationManager locman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final Localizacion Local = new Localizacion(lat,lon,men,calle);
        Local.setAnadirPunto(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locman.requestLocationUpdates(locman.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        men.setText("Localizado\n");

        if (!locman.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertNoGps();
        }
        location=locman.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        /**
         * boton de guardado
         */
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = nombre.getText().toString();
                final String description = descripcion.getText().toString();
                String latd=Local.getLatD();
                String lond=Local.getLonD();
                //     final double alt=location.getAltitude();

                ubicacionManager.insert(name, description,latd,lond);
                Intent main=new Intent(AddMarcador.this,BaseDatosVisu.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(main);
            }
        });

        // setLoc(location);
    }



    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }



}

