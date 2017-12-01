package com.example.jhorje.sqlcolegio;

/**
 * Created by JHORJE on 1/12/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.Objetos.Profesor;

/**
 * Created by jmalberola.
 */
public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbCole.db";
    private static final String DATABASE_TABLE_ESTUDIANTES = "estudiante";
    private static final String DATABASE_TABLE_PROFESORES = "estudiante";
    private static final int DATABASE_VERSION = 1;

    //Campos
    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";
    private static final String DESPACHO = "despacho";

    //Orden SQL
    private static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE_ESTUDIANTES+" (id integer primary key autoincrement, nombre text,edad integer, ciclo text, curso text, nota float); " +
            "CREATE TABLE "+DATABASE_TABLE_PROFESORES+" (id integer primary key autoincrement, nombre text,edad integer, ciclo text, curso text, despacho text);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ESTUDIANTES+"; DROP TABLE IF EXISTS "+DATABASE_TABLE_PROFESORES+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarEstudiante(Estudiante estudiante){
        //Creamos registro
        ContentValues nuevoReg = new ContentValues();

        //Asignar campos
        nuevoReg.put(NOMBRE,estudiante.getNombre());
        nuevoReg.put(EDAD,estudiante.getEdad());
        nuevoReg.put(CICLO,estudiante.getCiclo());
        nuevoReg.put(CURSO,estudiante.getCurso());
        nuevoReg.put(NOTA,estudiante.getNota());

        //Insertamos valores
        db.insert(DATABASE_TABLE_ESTUDIANTES,null,nuevoReg);
    }

    public void insertarProfesor(Profesor profesor){
        //Creamos registro
        ContentValues nuevoReg = new ContentValues();

        //Asignamos campos
        nuevoReg.put(NOMBRE,profesor.getNombre());
        nuevoReg.put(EDAD,profesor.getEdad());
        nuevoReg.put(CICLO,profesor.getCiclo());
        nuevoReg.put(CURSO,profesor.getCurso());
        nuevoReg.put(DESPACHO,profesor.getDespacho());

        //Insertamos valores
        db.insert(DATABASE_TABLE_PROFESORES,null,nuevoReg);
    }

    public void eliminarEstudiante(int id){
        db.delete(DATABASE_TABLE_ESTUDIANTES,"id" + "=" + id, null);
    }

    public void eliminarProfesor(int id){
        db.delete(DATABASE_TABLE_PROFESORES,"id" + "=" + id, null);
    }

    public void eliminarBBDD() {
        db.execSQL(DATABASE_DROP);
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }
}