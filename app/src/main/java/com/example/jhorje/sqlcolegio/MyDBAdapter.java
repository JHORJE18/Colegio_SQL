package com.example.jhorje.sqlcolegio;

/**
 * Created by JHORJE on 1/12/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.Objetos.Profesor;

import java.util.ArrayList;

/**
 * Created by jmalberola.
 */
public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbCole.db";
    private static final String DATABASE_TABLE_ESTUDIANTES = "estudiantes";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final int DATABASE_VERSION = 3;

    //Campos
    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";
    private static final String DESPACHO = "despacho";

    //Orden SQL
    private static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE_ESTUDIANTES+" (id integer primary key autoincrement, nombre text,edad integer, ciclo text, curso text, nota float); ";
    private static final String DATABASE_PROFESORES = "CREATE TABLE "+DATABASE_TABLE_PROFESORES+" (id integer primary key autoincrement, nombre text,edad integer, ciclo text, curso text, despacho text);";
    private static final String DATABASE_DROP = "DROP DATABASE " + DATABASE_NAME + ";";
    private static final String DATABASE_DROP_ESTUDIANTES = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ESTUDIANTES+";";
    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROFESORES+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
            Log.d("#TEMP","No iva tio!");
        }

    }

    public void close(){
        db.close();
    }

    public void insertarEstudiante(Estudiante estudiante){
        //Creamos registro
        ContentValues nuevoReg = new ContentValues();

        //Asignamos campos
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

    public int contarRegistrosEstudiantes(){
        Cursor cursorEstudiantes = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_ESTUDIANTES + ";",null);
        int totalEstudiantes = cursorEstudiantes.getCount();

        return totalEstudiantes;
    }

    public int contarRegistrosProfesores(){
        Cursor cursosProfesores = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_PROFESORES + ";",null);
        int totalProfesores = cursosProfesores.getCount();

        return totalProfesores;
    }

    public ArrayList<Estudiante> llenarEstudiantes(){
        Cursor cursorEstudiantes = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_ESTUDIANTES + ";",null);
        ArrayList<Estudiante> todosEstudiantes = new ArrayList<Estudiante>();

        if (cursorEstudiantes.moveToFirst()) {
            do {
                String nombre = cursorEstudiantes.getString(1);
                int edad = cursorEstudiantes.getInt(2);
                String ciclo = cursorEstudiantes.getString(3);
                String curso = cursorEstudiantes.getString(4);
                float nota = cursorEstudiantes.getFloat(5);

                Estudiante nuevo = new Estudiante(nombre,edad,ciclo,curso,nota);
                todosEstudiantes.add(nuevo);
            } while (cursorEstudiantes.moveToNext());
        }

        return todosEstudiantes;
    }

    public ArrayList<Estudiante> filtroEstudiantes(String filtros){
        Cursor cursorEstudiantes = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_ESTUDIANTES + filtros + ";",null);
        ArrayList<Estudiante> todosEstudiantes = new ArrayList<Estudiante>();

        if (cursorEstudiantes.moveToFirst()) {
            do {
                String nombre = cursorEstudiantes.getString(1);
                int edad = cursorEstudiantes.getInt(2);
                String ciclo = cursorEstudiantes.getString(3);
                String curso = cursorEstudiantes.getString(4);
                float nota = cursorEstudiantes.getFloat(5);

                Estudiante nuevo = new Estudiante(nombre,edad,ciclo,curso,nota);
                todosEstudiantes.add(nuevo);
            } while (cursorEstudiantes.moveToNext());
        }
        Toast.makeText(context.getApplicationContext(),"SQL: " + "SELECT * FROM " + DATABASE_TABLE_ESTUDIANTES + filtros + ";", Toast.LENGTH_LONG).show();

        return todosEstudiantes;
    }

    public ArrayList<Profesor> llenarProfesores(){
        Cursor cursorEstudiantes = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_PROFESORES + ";",null);
        ArrayList<Profesor> todosEstudiantes = new ArrayList<Profesor>();

        if (cursorEstudiantes.moveToFirst()) {
            do {
                String nombre = cursorEstudiantes.getString(1);
                int edad = cursorEstudiantes.getInt(2);
                String ciclo = cursorEstudiantes.getString(3);
                String curso = cursorEstudiantes.getString(4);
                String despacho = cursorEstudiantes.getString(5);

                Profesor nuevo = new Profesor(nombre,edad,ciclo,curso,despacho);
                todosEstudiantes.add(nuevo);
            } while (cursorEstudiantes.moveToNext());
        }

        return todosEstudiantes;
    }

    public ArrayList<Profesor> filtroProfesores(String filtros){
        Cursor cursorProfesores = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_PROFESORES + filtros + ";",null);
        ArrayList<Profesor> todoProfesores = new ArrayList<Profesor>();

        if (cursorProfesores.moveToFirst()) {
            do {
                String nombre = cursorProfesores.getString(1);
                int edad = cursorProfesores.getInt(2);
                String ciclo = cursorProfesores.getString(3);
                String curso = cursorProfesores.getString(4);
                String despacho = cursorProfesores.getString(5);

                Profesor nuevo = new Profesor(nombre,edad,ciclo,curso,despacho);
                todoProfesores.add(nuevo);
            } while (cursorProfesores.moveToNext());
        }
        Toast.makeText(context.getApplicationContext(),"SQL: " + "SELECT * FROM " + DATABASE_TABLE_ESTUDIANTES + filtros + ";", Toast.LENGTH_LONG).show();

        return todoProfesores;
    }

    public void eliminarEstudiante(int id){
        db.delete(DATABASE_TABLE_ESTUDIANTES,"id" + "=" + id, null);
    }

    public void eliminarProfesor(int id){
        db.delete(DATABASE_TABLE_PROFESORES,"id" + "=" + id, null);
    }

    public void eliminarBBDD() {
        context.deleteDatabase(DATABASE_NAME);
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("#TEMP",DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_PROFESORES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_ESTUDIANTES);
            db.execSQL(DATABASE_DROP_PROFESORES);
            onCreate(db);
        }

    }
}