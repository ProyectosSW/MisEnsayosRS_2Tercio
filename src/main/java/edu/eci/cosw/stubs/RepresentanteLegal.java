/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.stubs;

/**
 *
 * @author estudiante
 */
public class RepresentanteLegal {
    
    private String nombre;
    private String id;
    private String tipo_id;        

    public RepresentanteLegal(String nombre, String id, String tipo_id) {
        this.nombre = nombre;
        this.id = id;
        this.tipo_id = tipo_id;
    }

    public RepresentanteLegal() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
    
}
