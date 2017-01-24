package com.desarrollador.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 13/1/2017.
 */

public class UbicacionManager {
    private UbicacionDBHelper dbHelper;
    private SQLiteDatabase database;

    private  Context context;


    public UbicacionManager(Context c) {
       context=c;
    }

    public  UbicacionManager open() throws SQLException{
        dbHelper=new UbicacionDBHelper(context);
        database= dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){

        dbHelper.close();
    }

   /*
    public void insertIma(String ima){
        ContentValues contentValues=new ContentValues();
        contentValues.put(UbicacionDBHelper.KEY_IMAGE,ima);
        database.insert(UbicacionDBHelper.SQLITE_TABLE,null,contentValues);
    }

    */
    public void insert(String name,String desc,String lat,String lon){
        ContentValues contentValues=new ContentValues();
        contentValues.put(UbicacionDBHelper.KEY_NAME,name);
        contentValues.put(UbicacionDBHelper.KEY_DESCRIPTION,desc);
        contentValues.put(UbicacionDBHelper.KEY_LAT,lat);
        contentValues.put(UbicacionDBHelper.KEY_LON,lon);
        database.insert(UbicacionDBHelper.SQLITE_TABLE,null,contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[]{
                UbicacionDBHelper._ID,
                UbicacionDBHelper.KEY_NAME,
                UbicacionDBHelper.KEY_DESCRIPTION,
                UbicacionDBHelper.KEY_LAT,
                UbicacionDBHelper.KEY_LON};
        Cursor cursor = database.query(UbicacionDBHelper.SQLITE_TABLE,columns,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id,String name,String desc){
        ContentValues contentValues= new ContentValues();
        contentValues.put(UbicacionDBHelper.KEY_NAME,name);
        contentValues.put(UbicacionDBHelper.KEY_DESCRIPTION,desc);
        int i=database.update(UbicacionDBHelper.SQLITE_TABLE,contentValues,UbicacionDBHelper._ID+"="+_id,null);
        return  i;
    }

    public  void delete(long _id){
        database.delete(UbicacionDBHelper.SQLITE_TABLE,UbicacionDBHelper._ID+"="+_id,null);
    }

    public Cursor readData(){
        String allColums[]=new String[]{UbicacionDBHelper._ID,UbicacionDBHelper.KEY_NAME,UbicacionDBHelper.KEY_DESCRIPTION,UbicacionDBHelper.KEY_LAT,UbicacionDBHelper.KEY_LON};
        Cursor c= database.query(UbicacionDBHelper.SQLITE_TABLE,allColums,null,null,null,null,null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }
    public  Cursor getLati_Lon(String titleSelction){
        String selecionArgs[]=new String[]{titleSelction};
        String query=
                "select "+UbicacionDBHelper._ID+","+UbicacionDBHelper.KEY_LAT+","+UbicacionDBHelper.KEY_LON+
                        " from "+UbicacionDBHelper.SQLITE_TABLE +
                        " where "+UbicacionDBHelper.KEY_NAME+
                        "= ?";
        return database.rawQuery(query,selecionArgs);
    }





}

