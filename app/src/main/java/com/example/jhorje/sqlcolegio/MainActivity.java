package com.example.jhorje.sqlcolegio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables
    public MyDBAdapter dbAdapter;
    Button btnNuevo, btnRecargar, btnEliminar;
    TextView txtCountEstudiantes, txtCountProfesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conexion elementos vista
        btnNuevo = (Button) findViewById(R.id.btnAñadir);
        btnRecargar = (Button) findViewById(R.id.btnRefrescar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        txtCountEstudiantes = (TextView) findViewById(R.id.txtCountEstudiantes);
        txtCountProfesores = (TextView) findViewById(R.id.txtCountProfesores);

        //Cargamos Base de datos SQLite
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();
        recargar();
    }

    @Override
    protected void onResume() {
        recargar();
        super.onResume();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnAñadir:
                Intent ventanaNuevo = new Intent(this,NuevoCampoActivity.class);
                startActivity(ventanaNuevo);
                break;
            case R.id.btnRefrescar:
                recargar();
                Toast.makeText(getApplicationContext(),"Recargando elementos",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEliminar:
                Intent ventanaEliminar = new Intent(this, EliminarCampoActivity.class);
                startActivity(ventanaEliminar);
                break;
            case R.id.btnRegistros:
                Intent ventanaRegistros = new Intent(this, MostrarRegistrosActivity.class);
                startActivity(ventanaRegistros);
                break;
        }
    }

    public void recargar(){
        //Contadores
        int estudiantes = 0;
        try {
            estudiantes = dbAdapter.contarRegistrosEstudiantes();
        }catch (Exception e){
            Log.w("#TEMP","Error primo!");
        }
        txtCountEstudiantes.setText("Estudiantes: " + estudiantes);
        int profesores = 0;
        try {
            profesores = dbAdapter.contarRegistrosProfesores();
        }catch (Exception e){
            Log.w("#TEMP","Error primo!");
        }
        txtCountProfesores.setText("Profesores: " + profesores);
    }
}
