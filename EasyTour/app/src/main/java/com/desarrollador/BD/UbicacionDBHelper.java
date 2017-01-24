package com.desarrollador.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Diego on 13/1/2017.
 */

public class UbicacionDBHelper extends SQLiteOpenHelper {
    //String sqlCreate="CREATE TABLE Ubicacion (nombre TEXT + descripcion TEXT)";
    //Nombre de la Tabla
    public static final String SQLITE_TABLE="db";

    public static  final  int DATABASE_VERSION=1;

    //Nombre a nivel de archivos
    public  static final String DATABASE_NAME="dbase.db";

    //Database Columnas
    public static final String _ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LON = "lon";
  //  public static final String KEY_IMAGE="image";




    public static final  String DATABASE_CREATE="CREATE TABLE if not exists "+SQLITE_TABLE+"("
            +_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_NAME +" TEXT NOT NULL, "
            +KEY_DESCRIPTION+"  TEXT,"
            +KEY_LAT+" TEXT,"
            +KEY_LON+" TEXT"+");";
    public static SQLiteDatabase.CursorFactory factory=null;
    public UbicacionDBHelper(Context context) {
        super(context,DATABASE_NAME, factory,DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Creando base de datos");
        db.execSQL(DATABASE_CREATE);
        Log.i(this.getClass().toString(), "Tabla DATA creada");
        db.execSQL("INSERT INTO "+SQLITE_TABLE+"("+_ID+","+KEY_NAME+","+KEY_DESCRIPTION+","+KEY_LAT+","+KEY_LON+")VALUES(1,'Prueba','EN los jardines dela vida',0.3333,6.53433)");
        db.execSQL("INSERT INTO "+SQLITE_TABLE+"("+_ID+","+KEY_NAME+","+KEY_DESCRIPTION+","+KEY_LAT+","+KEY_LON+") VALUES(2, 'Prueba2','Es to es el cileo',0.3333,6.53433)");
        Log.i(this.getClass().toString(), "Guardar Datos iniciales Ubicaciones insertados");
        Log.i(this.getClass().toString(), "Base de datos creada");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+SQLITE_TABLE);
        onCreate(db);
    }

    public ArrayList<String > getAllNames(){
        ArrayList<String> list=new ArrayList<String>();
        SQLiteDatabase db= this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery="SELECT * FROM "+SQLITE_TABLE;
            Cursor cursor=db.rawQuery(selectQuery,null);
            int cero=0;
            if(cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    String pname=cursor.getString(cursor.getColumnIndex("pname"));
                    list.add(pname);
                }

            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }



}
/**
 //db.execSQL("CREATE UNIQUE INDEX nombre ON DATA(nombre ASC)");
 Log.i(this.getClass().toString(), "Tabla DATA creada");
 db.execSQL("INSERT INTO DATA(nombre,description) VALUES('Prueba','EN los jardines d ela vida ')");
 db.execSQL("INSERT INTO DATA(nombre,description) VALUES('Prueba2','Es to es el cileo')");
 Log.i(this.getClass().toString(), "GuardarDatos iniciales Ubicaciones insertados");
 Log.i(this.getClass().toString(), "Base de datos creada");
 */
