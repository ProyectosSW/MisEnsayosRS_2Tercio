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
    
    /**
     * 
     * @param id
     * @param nombreComercial
     * @param fechaConstitucion
     * @param representantelegal 
     */
    public Empresa(String id, String nombreComercial, String fechaConstitucion, RepresentanteLegal representantelegal) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.fechaConstitucion = fechaConstitucion;
        this.representantelegal = representantelegal;
    }
    
    /**
     * 
     */
    public Empresa() {
    }
    
    /**
     * 
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return 
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    /**
     * 
     * @return 
     */
    public String getFechaConstitucion() {
        return fechaConstitucion;
    }

    /**
     * 
     * @param fechaConstitucion 
     */
    public void setFechaConstitucion(String fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    /**
     * 
     * @return 
     */
    public RepresentanteLegal getRepresentantelegal() {
        return representantelegal;
    }

    /**
     * 
     * @param representantelegal 
     */ 
    public void setRepresentantelegal(RepresentanteLegal representantelegal) {
        this.representantelegal = representantelegal;
    }
    
}
