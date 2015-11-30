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
public class Empresa {
    
    private String id;
    private String nombreComercial;
    private String fechaConstitucion;
    private RepresentanteLegal representantelegal;

    public Empresa(String id, String nombreComercial, String fechaConstitucion, RepresentanteLegal representantelegal) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.fechaConstitucion = fechaConstitucion;
        this.representantelegal = representantelegal;
    }

    public Empresa() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(String fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    public RepresentanteLegal getRepresentantelegal() {
        return representantelegal;
    }

    public void setRepresentantelegal(RepresentanteLegal representantelegal) {
        this.representantelegal = representantelegal;
    }
    
}
