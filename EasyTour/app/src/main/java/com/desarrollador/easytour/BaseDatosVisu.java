package com.desarrollador.easytour;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.desarrollador.BD.UbicacionDBHelper;
import com.desarrollador.BD.UbicacionManager;

public class BaseDatosVisu  extends AppCompatActivity {
    private UbicacionManager ubicacionManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{UbicacionDBHelper._ID, UbicacionDBHelper.KEY_NAME, UbicacionDBHelper.KEY_DESCRIPTION,UbicacionDBHelper.KEY_LAT,UbicacionDBHelper.KEY_LON};

    final int[] to = new int[]{R.id.id, R.id.titulo,R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos_visu);
        ubicacionManager = new UbicacionManager(this);
        ubicacionManager.open();
        Cursor cursor = ubicacionManager.fetch();
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_regis, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView idTv = (TextView) view.findViewById(R.id.id);
                TextView tituloTv = (TextView) view.findViewById(R.id.titulo);
                TextView descTv = (TextView) view.findViewById(R.id.desc);


                String idd = idTv.getText().toString();
                String ti = tituloTv.getText().toString();
                String de = descTv.getText().toString();


                Intent modify_intent = new Intent(BaseDatosVisu.this, ModificarUbica.class);
                modify_intent.putExtra("title", ti);
                modify_intent.putExtra("desc", de);
                modify_intent.putExtra("id", idd);

                startActivity(modify_intent);
            }


        });
}


}