package com.example.jhorje.sqlcolegio.Objetos;

/**
 * Created by JHORJE on 22/1/18.
 */

public class Asignatura {

    //Variabes
    String nombre;
    String horas;

    public Asignatura(String nombre, String horas) {
        this.nombre = nombre;
        this.horas = horas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
}
