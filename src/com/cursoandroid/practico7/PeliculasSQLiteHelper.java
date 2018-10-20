package com.cursoandroid.practico7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PeliculasSQLiteHelper extends SQLiteOpenHelper 
{
    String sqlCreate = "CREATE TABLE Peliculas (ranking INTEGER, titulo TEXT, anio INTEGER)";
 
    public PeliculasSQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) 
    {
        super(contexto, nombre, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) 
    {
        db.execSQL(sqlCreate);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) 
    {        
        db.execSQL("DROP TABLE IF EXISTS Peliculas");
        db.execSQL(sqlCreate);
    }
}
