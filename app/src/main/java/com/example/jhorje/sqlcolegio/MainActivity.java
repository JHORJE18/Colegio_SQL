package com.example.jhorje.sqlcolegio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables
    public MyDBAdapter dbAdapter;
    Button btnNuevo, btnRecargar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conexion elementos vista
        btnNuevo = (Button) findViewById(R.id.btnAñadir);
        btnRecargar = (Button) findViewById(R.id.btnRefrescar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        //Cargamos Base de datos SQLite
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnAñadir:
                Intent ventanaNuevo = new Intent(this,NuevoCampoActivity.class);
                startActivity(ventanaNuevo);
                break;
            case R.id.btnRefrescar:
                Toast.makeText(getApplicationContext(),"Recargando elementos",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEliminar:
                Intent ventanaEliminar = new Intent(this, EliminarCampoActivity.class);
                startActivity(ventanaEliminar);
                break;
        }
    }
}
