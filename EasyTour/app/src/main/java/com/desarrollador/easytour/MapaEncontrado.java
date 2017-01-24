package com.desarrollador.easytour;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MapaEncontrado extends FragmentActivity implements OnMapReadyCallback {

    private static final String LOGTAG = "android-localizacion";
    private GoogleMap mMap;
    private UiSettings mUiSettings;

    private EditText editLong;
    private EditText editLat;
    private EditText editAlt;
    private double longitud;
    private double latitud;
    private double altitud;
    private String direccion;
//    private Button btnEncontar;

    private List<LatLng> puntos = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_encontrado);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //this.getActionBar().setDisplayHomeAsUpEnabled(true);

        //Localizar los controles
        /*editLong = (EditText)findViewById(R.id.txtLongitud);
        editLat = (EditText)findViewById(R.id.txtLatitud);
        editAlt = (EditText)findViewById(R.id.txtAltitud);*/

        //Conversion
        /*longitud = Double.parseDouble(editLong.toString());
        latitud = Double.parseDouble(editLat.toString());
        altitud = Double.parseDouble(editAlt.toString());*/

        //Recuperamos la información pasada en el intent
/*        Bundle traee = this.getIntent().getExtras();
        longitud = Double.parseDouble(traee.getString("Longitud"));
        latitud = Double.parseDouble(traee.getString("Latitud"));
        altitud = Double.parseDouble(traee.getString("Altitud"));
        direccion = traee.getString("Calles");

        puntos = traee.getParcelableArrayList("Coordenadas");

        mostrarRuta();
*/

//        longitud = Double.parseDouble(getIntent().getExtras().getString("Longitud"));
//        latitud = Double.parseDouble(getIntent().getExtras().getString("Latitud"));
//        altitud = Double.parseDouble(getIntent().getExtras().getString("Altitud"));

        //Construimos el marcador
//        txtSaludo.setText("Hola " + bundle.getString("NOMBRE"));
//        LatLng localizado = new LatLng(longitud, latitud);
//        setMarker(localizado,"TItulo","Info"); // Agregamos el marcador verde
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //BOTON ATRAS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();


        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Log.e(LOGTAG, "Se requiere permiso del gps del usuario para mostrar la ubicación");
        }*/

        //Controles UI
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        //mUiSettings.setMyLocationButtonEnabled(true); //activa boton ubicacion actual

        //Coordenadas del AddMarcador
/*        LatLng localizado = new LatLng(latitud, longitud);

        //AÑADIR ALTITUD
        MarkerOptions marcador =
                new MarkerOptions()
                        .position(localizado)
                        .title("Ubicación: " + longitud + ", " + latitud)
                        .snippet("Calle: " + direccion);

        mMap.addMarker(marcador);
        CameraUpdate mCamara = CameraUpdateFactory.newLatLng(localizado);
        mMap.moveCamera(mCamara);
        //mMap.animateCamera(CameraUpdateFactory.zoomIn());
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizado, 18));
        //CENTRA el marcador en el mapa y con nivel de... 18
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(localizado));
*/
        //todo el mapa ubica en centro el marcador

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Log.e(LOGTAG, "Se requiere permiso del gps del usuario para mostrar la ubicación");
        }
    }

    /*
    private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        Marker myMaker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))); //Color del marcador
    }
    */

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MapaEncontrado Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    private void mostrarRuta()
    {
        //Dibujo con Lineas
        PolylineOptions lineas = new PolylineOptions();
        lineas.addAll(puntos);
        lineas.width(3);
        lineas.color(Color.RED);
//revisar los puntos agregados
        mMap.addPolyline(lineas);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}
