/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.stubs;

/**
 *
 * @author LuisCarlos
 */
public class DetalleCalificacion implements java.io.Serializable {
    int idInstrumento;
    String nombreInstrumento;
    
    public DetalleCalificacion(){
        
    }
    public DetalleCalificacion(int idInstrumento, String nombreInstrumento){
        this.idInstrumento=idInstrumento;
        this.nombreInstrumento=nombreInstrumento;
    }
    public int getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(int idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public String getNombreInstrumento() {
        return nombreInstrumento;
    }

    public void setNombreInstrumento(String nombreInstrumento) {
        this.nombreInstrumento = nombreInstrumento;
    }
    
}
