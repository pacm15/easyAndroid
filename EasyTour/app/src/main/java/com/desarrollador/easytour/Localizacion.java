package com.desarrollador.easytour;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Diego on 13/1/2017.
 */


public class Localizacion  implements LocationListener {
        AddMarcador anadirPunto;
        TextView latitud;
        TextView longitud;
        Location location;
        TextView calle;
        TextView men;
        //Metodos set y get

        public Localizacion(TextView lat, TextView longitud, TextView c,TextView m){
            setLatView(lat);
            setLonView(longitud);
            setCalle(c);
            setMen(m);
        }

        //Para el activity

        public void setLatView(TextView lat){

            latitud=lat;
        }
        public void setLonView(TextView lon){

            longitud=lon;
        }
        public void setCalle(TextView c) {

            calle = c;
        }
        public void setMen(TextView m) {

            men = m;
        }
        public void setAnadirPunto(AddMarcador main){

            anadirPunto =main;
        }
        public AddMarcador getAnadirPunto(){
            return anadirPunto;
        }
        public  TextView getLatitud(){
            return latitud;
        }
        public  TextView getLongitud(){

            return  longitud;
        }
        public Location getLoc() {
            return location;
        }
        public TextView getCalle() {

            return calle;
        }
        public TextView getMen() {

            return men;
        }

        public String  getLatD(){
            return String.valueOf(getLatitud());
        }
        public String getLonD(){
            return String.valueOf(getLongitud());
        }


        @Override
        public void onLocationChanged(Location location) {
            location.getLatitude();
            location.getLongitude();
            location.getSpeed();
            String lat=String.valueOf(location.getLatitude());
            String lon=String.valueOf(location.getLongitude());
            getLatitud().setText(lat);
            getLongitud().setText(lon);

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {

            getMen().setText("Activado");
        }
        @Override
        public void onProviderDisabled(String provider) {

            getMen().setText("Desactivado");
        }
    }


