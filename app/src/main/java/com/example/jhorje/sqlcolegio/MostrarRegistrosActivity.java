package com.example.jhorje.sqlcolegio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.Objetos.Profesor;

import java.util.ArrayList;

public class MostrarRegistrosActivity extends AppCompatActivity {

    //TODO https://androidstudiofaqs.com/tutoriales/adapter-personalizado-en-android
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registros);
    }
}
