package com.desarrollador.easytour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.desarrollador.BD.UbicacionManager;

/**
 * Created by Diego on 13/1/2017.
 */
public class ModificarUbica extends Activity implements View.OnClickListener {

    private EditText titleText,descText;
    private Button modificar,eliminar;
    private long _id;
    private  UbicacionManager ubicacionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_ubica);
        setTitle("Modificar Registro");

        ubicacionManager=new UbicacionManager(this);
        ubicacionManager.open();

        titleText=(EditText)findViewById(R.id.modi_nombre);
        descText=(EditText)findViewById(R.id.modi_desc);


        modificar=(Button)findViewById(R.id.update);
        eliminar=(Button)findViewById(R.id.delete);

        Intent intent=getIntent();

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("titulo");
        String desc = intent.getStringExtra("desc");


        _id=Long.parseLong(id);
        titleText.setText(name);
        descText.setText(desc);

        modificar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update:
                String nombre= titleText.getText().toString();
                String desc= descText.getText().toString();
                ubicacionManager.update(_id,nombre,desc);
                this.returnHome();
                break;

            case R.id.delete:
                ubicacionManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome(){
        Intent home=new Intent(getApplicationContext(), Pantalla2.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home);


    }

}
